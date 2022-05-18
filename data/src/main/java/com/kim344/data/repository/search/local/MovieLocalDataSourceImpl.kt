package com.kim344.data.repository.search.local

import com.kim344.data.db.MovieDao
import com.kim344.data.model.MovieEntity
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class MovieLocalDataSourceImpl @Inject constructor(private val movieDao: MovieDao): MovieLocalDataSource {
    override fun insertMovie(movies: List<MovieEntity>): Completable {
        return movieDao.insertMovies(movies)
    }

    override fun getAllMovies(): Single<List<MovieEntity>> {
        return movieDao.getAllMovies()
    }

    override fun getSearchMovies(title: String): Single<List<MovieEntity>> {
        return movieDao.getMoviesByTitle(title)
    }

    override fun deleteAllMovies(): Completable {
        return movieDao.deleteAllMovies()
    }
}