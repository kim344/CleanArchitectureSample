package com.kim344.cleanarchitecturesample.views.user

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
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

        viewModel.requestRandomUserData("10")
        viewModel.requestUserData("kim344")

        initViewModelCallback()
    }

    private fun initViewModelCallback() {
        with(viewModel) {
            userData.observe(this@UserActivity) {
                Log.e("TAG","userData = $it")
            }

            randomUserData.observe(this@UserActivity) {
                Log.e("TAG","RandomUser = $it")

                userAdapter = UserAdapter(it.results) {
                    // 외부 URL 호출
                    Log.e("TAG","Item Click = $it")
                }

                binding.rvMovies.apply {
                    layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
                    adapter = userAdapter
                }
            }
        }
    }

}