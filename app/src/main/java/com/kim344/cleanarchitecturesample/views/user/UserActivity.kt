package com.kim344.cleanarchitecturesample.views.user

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.kim344.cleanarchitecturesample.R
import com.kim344.cleanarchitecturesample.base.BaseActivity
import com.kim344.cleanarchitecturesample.databinding.ActivityUserBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserActivity : BaseActivity<ActivityUserBinding>(R.layout.activity_user) {

    private val viewModel: UserViewModel by viewModels()
    lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        //viewModel.requestRandomUserData("10")
        viewModel.requestUserData("kim344")

        userAdapter = UserAdapter {
            Log.e("TAG","Adapter Click = $it")
        }

        binding.rvMovies.adapter = userAdapter

        initViewModelCallback()
    }

    private fun initViewModelCallback() {
        with(viewModel) {
            userData.observe(this@UserActivity) {
                Log.e("TAG","userData = $it")
            }

            randomUserData.observe(this@UserActivity) {
                Log.e("TAG","RandomUser = $it")
            }
        }
    }

}