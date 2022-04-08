package com.kim344.cleanarchitecturesample.views.search

import android.os.Bundle
import androidx.activity.viewModels
import com.kim344.cleanarchitecturesample.R
import com.kim344.cleanarchitecturesample.base.BaseActivity
import com.kim344.cleanarchitecturesample.databinding.ActivityMovieSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieSearchActivity: BaseActivity<ActivityMovieSearchBinding>(R.layout.activity_movie_search) {

    private val viewModel: MovieSearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
    }

}