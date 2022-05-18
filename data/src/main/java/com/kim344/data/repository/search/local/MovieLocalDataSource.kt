package com.kim344.data.repository.search.local

import com.kim344.data.model.MovieEntity
import io.reactivex.Completable
import io.reactivex.Single

interface MovieLocalDataSource {
    fun insertMovie(movies: List<MovieEntity>): Completable
    fun getAllMovies(): Single<List<MovieEntity>>
    fun getSearchMovies(title: String): Single<List<MovieEntity>>
    fun deleteAllMovies(): Completable
}