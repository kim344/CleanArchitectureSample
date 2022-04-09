package com.kim344.domain.repository

import com.kim344.domain.model.search.Movie
import io.reactivex.Flowable
import io.reactivex.Single

interface MovieRepository {
    fun getSearchMovies(
        query: String
    ): Flowable<List<Movie>>

    fun getLocalSearchMovie(
        query: String
    ): Flowable<List<Movie>>

    fun getRemoteSearchMovie(
        query: String
    ): Single<List<Movie>>

    fun getPagingMovies(
        query: String,
        offset: Int
    ): Single<List<Movie>>
}