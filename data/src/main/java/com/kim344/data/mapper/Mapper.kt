package com.kim344.data.mapper

import com.kim344.data.model.MovieEntity
import com.kim344.domain.search.Movie

fun mapperToMovie(movie: List<MovieEntity>): List<Movie> {
    return movie.toList().map {
        Movie(
            it.actor,
            it.director,
            it.image,
            it.link,
            it.pubDate,
            it.subtitle,
            it.title,
            it.userRating
        )
    }
}
