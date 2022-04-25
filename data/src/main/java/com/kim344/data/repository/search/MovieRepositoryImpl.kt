package com.kim344.data.repository.search

import com.kim344.data.repository.search.local.MovieLocalDataSource
import com.kim344.data.repository.search.remote.MovieRemoteDatasource
import com.kim344.domain.repository.MovieRepository
import com.kim344.domain.search.Movie
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieRemoteDatasource: MovieRemoteDatasource,
    private val movieLocalDataSource: MovieLocalDataSource
): MovieRepository {
    override fun getSearchMovies(query: String): Flowable<List<Movie>> {
        TODO("Not yet implemented")
    }

    override fun getLocalSearchMovies(query: String): Flowable<List<Movie>> {
        TODO("Not yet implemented")
    }

    override fun getRemoteSearchMovies(query: String): Single<List<Movie>> {
        TODO("Not yet implemented")
    }

    override fun getPagingMovies(query: String, offset: Int): Single<List<Movie>> {
        TODO("Not yet implemented")
    }
}