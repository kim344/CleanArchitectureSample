package com.kim344.cleanarchitecturesample.views.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kim344.cleanarchitecturesample.base.BaseViewModel
import com.kim344.cleanarchitecturesample.views.search.MovieSearchViewModel
import com.kim344.domain.search.Movie
import com.kim344.domain.search.User
import com.kim344.domain.usecase.GetUserUseCase
import com.kim344.domain.usecase.InsertLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val insertLoginUseCase: InsertLoginUseCase,
    private val userUseCase: GetUserUseCase
): BaseViewModel() {

    val id: MutableLiveData<String> = MutableLiveData("")
    val pw: MutableLiveData<String> = MutableLiveData("")
    private val _toastMsg = MutableLiveData<MovieSearchViewModel.MessageSet>()
    private val _isIdEmpty: MutableLiveData<Unit> = MutableLiveData()
    private val _isPwEmpty: MutableLiveData<Unit> = MutableLiveData()
    private val _loginErrorMsg: MutableLiveData<Unit> = MutableLiveData()
    private val _successLogin: MutableLiveData<Unit> = MutableLiveData()
    private val _userData: MutableLiveData<User> = MutableLiveData()

    val toastMsg: LiveData<MovieSearchViewModel.MessageSet> get() = _toastMsg
    val isIdEmpty: LiveData<Unit> get() = _isIdEmpty
    val isPwEmpty: LiveData<Unit> get() = _isPwEmpty
    val loginErrorMsg: LiveData<Unit> get() = _loginErrorMsg
    val successLogin: LiveData<Unit> get() = _successLogin
    val userData: LiveData<User> get() = _userData


    fun onLoginClick(){
        val id = id.value.toString().trim()
        val pw = pw.value.toString().trim()

        if (id.isEmpty()){
            _isIdEmpty.value = Unit
        } else if (pw.isEmpty()) {
            _isPwEmpty.value = Unit
        } else if (id != USER_ID || pw != USER_PW){
            _loginErrorMsg.value = Unit
        } else {
            insertLoginUseCase.execute(true)
            _successLogin.value = Unit
        }
    }

    fun requestUserData(userId: String) {
        compositeDisposable.add(
            userUseCase.execute(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showProgress() }
                .doAfterTerminate { hideProgress() }
                .subscribe({ userData ->

                    _userData.value = userData

                }, {
                    Log.e("Error","requestUserData Exception = ${it.message}")
                    _toastMsg.value = MovieSearchViewModel.MessageSet.ERROR
                })
        )
    }

    companion object { //이 아이디와 비번으로만 로그인이 가능 (서버X)
        private const val USER_ID = "id"
        private const val USER_PW = "pass"
    }
}