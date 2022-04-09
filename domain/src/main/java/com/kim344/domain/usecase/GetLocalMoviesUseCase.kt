package com.kim344.domain.usecase

import com.kim344.domain.model.search.Movie
import com.kim344.domain.repository.MovieRepository
import io.reactivex.Flowable
import javax.inject.Inject

class GetLocalMoviesUseCase @Inject constructor(private val repository: MovieRepository){
    fun execute(
        query: String
    ): Flowable<List<Movie>> = repository.getLocalSearchMovie(query)
}