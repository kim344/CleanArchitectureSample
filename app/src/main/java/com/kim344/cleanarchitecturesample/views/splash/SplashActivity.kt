package com.kim344.cleanarchitecturesample.views.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.kim344.cleanarchitecturesample.R
import com.kim344.cleanarchitecturesample.views.login.LoginActivity
import com.kim344.cleanarchitecturesample.views.search.MovieSearchActivity
import com.kim344.domain.test.GetTestUseCase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity: AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModels()

    @Inject
    lateinit var name: String

    @Inject
    lateinit var testUseCase: GetTestUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModelCallback()
        viewModel.doSplash()

        Log.e("확인",name)
        Log.e("확인","${testUseCase.setChangeValue("안녕하세요")}")
    }

    private fun initViewModelCallback(){
        with(viewModel){
            goMovieSearch.observe(this@SplashActivity, {
                goMovieSearch()
            })

            goLogin.observe(this@SplashActivity, {
                goLogin()
            })
        }
    }

    private fun goLogin(){
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun goMovieSearch(){
        showToast(getString(R.string.auto_login_msg))
        startActivity(Intent(this, MovieSearchActivity::class.java))
        finish()
    }

    private fun showToast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}