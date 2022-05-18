package com.kim344.data.repository.search.remote

import com.kim344.data.model.MovieResponse
import io.reactivex.Single

interface MovieRemoteDatasource {
    fun getSearchMovies(
        query: String,
        start: Int = 1
    ): Single<MovieResponse>
}