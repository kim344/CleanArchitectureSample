package com.kim344.cleanarchitecturesample.views.splash

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kim344.domain.usecase.GetTestUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val testUseCase: GetTestUseCase): ViewModel() {

    private val _goMovieSearch: MutableLiveData<Unit> = MutableLiveData()
    private val _goLogin: MutableLiveData<Unit> = MutableLiveData()

    val goMovieSearch: LiveData<Unit> get() = _goMovieSearch
    val goLogin: LiveData<Unit> get() = _goLogin

    fun doSplash() {
        Log.e("동주","splash test = ${testUseCase.execute()}")
//        if (getLoginUseCase.execute()){
//            _goMovieSearch.value = Unit
//        } else {
//            _goLogin.value = Unit
//        }
    }
}