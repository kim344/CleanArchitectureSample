package com.kim344.cleanarchitecturesample.views.user

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kim344.cleanarchitecturesample.base.BaseViewModel
import com.kim344.cleanarchitecturesample.views.search.MovieSearchViewModel
import com.kim344.domain.search.RandomUser
import com.kim344.domain.search.User
import com.kim344.domain.usecase.GetRandomUserUseCase
import com.kim344.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val getRandomUserUseCase: GetRandomUserUseCase
): BaseViewModel() {

    private val _userData: MutableLiveData<User> = MutableLiveData()
    private val _randomUserData: MutableLiveData<RandomUser> = MutableLiveData()

    val userData: LiveData<User> get() = _userData
    val randomUserData: LiveData<RandomUser> get() = _randomUserData


    fun requestUserData(userId: String) {
        compositeDisposable.add(
            getUserUseCase.execute(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showProgress() }
                .doAfterTerminate { hideProgress() }
                .subscribe({ userData ->

                    _userData.value = userData

                }, {
                    Log.e("Error","requestUserData Exception = ${it.message}")
                })
        )
    }

    fun requestRandomUserData(results: String) {
        compositeDisposable.add(
            getRandomUserUseCase.getRandomUserData(results)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showProgress() }
                .doAfterTerminate { hideProgress() }
                .subscribe({ randomUserData ->

                    _randomUserData.value = randomUserData

                }, {
                    Log.e("Error","requestUserData Exception = ${it.message}")
                })
        )
    }
}