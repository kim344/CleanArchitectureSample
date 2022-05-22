package com.kim344.cleanarchitecturesample.views.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kim344.cleanarchitecturesample.base.BaseViewModel
import com.kim344.cleanarchitecturesample.utils.NetworkManager
import com.kim344.data.utils.LAST_PAGE
import com.kim344.domain.search.Movie
import com.kim344.domain.usecase.GetLocalMoviesUseCase
import com.kim344.domain.usecase.GetMoviesUseCase
import com.kim344.domain.usecase.GetPagingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MovieSearchViewModel @Inject constructor(
    private val getMovieUseCase: GetMoviesUseCase,
    private val getPagingMoviesUseCase: GetPagingMoviesUseCase,
    private val getLocalMoviesUseCase: GetLocalMoviesUseCase,
    private val networkManager: NetworkManager
): BaseViewModel() {

    private var currentQuery: String = ""
    val query = MutableLiveData<String>()
    private val _movieList = MutableLiveData<MutableList<Movie>>()
    private val _toastMsg = MutableLiveData<MessageSet>()

    val movieList: LiveData<MutableList<Movie>> get() = _movieList
    val toastMsg: LiveData<MessageSet> get() = _toastMsg

    fun requestMovie() {
        currentQuery = query.value.toString().trim()
        if (currentQuery.isEmpty()){
            _toastMsg.value = MessageSet.EMPTY_QUERY
            return
        }

        if (!checkNetworkState()) return
        compositeDisposable.add(
            getMovieUseCase.execute(currentQuery)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showProgress() }
                .doAfterTerminate { hideProgress() }
                .subscribe({ movies ->
                    if (movies.isEmpty()){
                        _toastMsg.value = MessageSet.NO_RESULT
                    } else {
                        _movieList.value = movies as ArrayList<Movie>
                        _toastMsg.value = MessageSet.SUCCESS
                    }
                }, {
                    _toastMsg.value = MessageSet.ERROR
                })
        )
    }

    fun requestPagingMovie(offSet: Int){
        if (!checkNetworkState()) return
        compositeDisposable.add(
            getPagingMoviesUseCase.execute(currentQuery, offSet)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showProgress() }
                .doAfterTerminate { hideProgress() }
                .subscribe({ movies ->
                    val pagingMovieList = _movieList.value
                    pagingMovieList?.addAll(movies)
                    _movieList.value = pagingMovieList
                    _toastMsg.value = MessageSet.SUCCESS
                }, {
                    when (it.message){
                        LAST_PAGE -> _toastMsg.value = MessageSet.LAST_PAGE
                        else -> {
                            _toastMsg.value = MessageSet.ERROR
                        }
                    }
                })
        )
    }

    private fun requestLocalMovies(){
        compositeDisposable.add(
            getLocalMoviesUseCase.execute(currentQuery)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showProgress() }
                .doAfterTerminate { hideProgress() }
                .subscribe({ movies ->
                    if (movies.isEmpty()){
                        _toastMsg.value = MessageSet.NETWORK_NOT_CONNECTED
                    } else {
                        _movieList.value = movies as ArrayList<Movie>
                        _toastMsg.value = MessageSet.LOCAL_SUCCESS
                    }
                }, {
                    _toastMsg.value = MessageSet.NETWORK_NOT_CONNECTED
                })
        )
    }

    private fun checkNetworkState(): Boolean {
        return if (networkManager.checkNetworkState()) {
            true
        } else {
            requestLocalMovies()
            false
        }
    }

    enum class MessageSet {
        LAST_PAGE,
        EMPTY_QUERY,
        NETWORK_NOT_CONNECTED,
        ERROR,
        SUCCESS,
        NO_RESULT,
        LOCAL_SUCCESS
    }
}