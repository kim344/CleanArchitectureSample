package com.kim344.cleanarchitecturesample.views.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.kim344.cleanarchitecturesample.R
import com.kim344.cleanarchitecturesample.base.BaseActivity
import com.kim344.cleanarchitecturesample.databinding.ActivityLoginBinding
import com.kim344.cleanarchitecturesample.views.search.MovieSearchActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity: BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        initViewModelCallback()

        binding.ivLogo.setOnClickListener {
            viewModel.requestUserData("kim344")
        }
    }

    private fun initViewModelCallback(){
        with(viewModel){

            isIdEmpty.observe(this@LoginActivity, {
                showIdEmptyError()
            })

            isPwEmpty.observe(this@LoginActivity, {
                showPwEmptyError()
            })

            loginErrorMsg.observe(this@LoginActivity, {
                showToast(getString(R.string.id_pw_not_correct_error_msg))
            })

            successLogin.observe(this@LoginActivity, {
                goMovieSearch()
            })

            toastMsg.observe(this@LoginActivity, {
                showToast(getString(R.string.error_msg))
            })

            userData.observe(this@LoginActivity, {
                binding.etId.setText(it.login)
            })
        }
    }

    private fun showIdEmptyError(){
        binding.etId.error = getString(R.string.id_empty_error_msg)
    }

    private fun showPwEmptyError(){
        binding.etPw.error = getString(R.string.pw_empty_error_msg)
    }

    private fun goMovieSearch(){
        showToast(getString(R.string.login_success_msg))
        startActivity(Intent(this, MovieSearchActivity::class.java))
        finish()
    }
}