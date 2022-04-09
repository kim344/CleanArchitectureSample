package com.kim344.domain.usecase

import com.kim344.domain.model.search.Movie
import com.kim344.domain.repository.MovieRepository
import io.reactivex.Single
import javax.inject.Inject

class GetPagingMovieUseCase @Inject constructor(private val repository: MovieRepository) {
    fun execute(
        query: String,
        offset: Int
    ): Single<List<Movie>> = repository.getPagingMovies(query, offset)
}