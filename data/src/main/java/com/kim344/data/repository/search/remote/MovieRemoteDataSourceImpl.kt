package com.kim344.data.repository.search.remote

import com.kim344.data.api.ApiInterface
import com.kim344.data.model.MovieResponse
import io.reactivex.Single
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(private val apiInterface: ApiInterface): MovieRemoteDatasource {
    override fun getSearchMovies(query: String, start: Int): Single<MovieResponse> {
        return apiInterface.getSearchMovie(query, start)
    }
}