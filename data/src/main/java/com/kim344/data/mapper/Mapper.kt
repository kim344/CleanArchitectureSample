package com.kim344.data.mapper

import com.kim344.data.model.MovieEntity
import com.kim344.data.model.RandomUserResponse
import com.kim344.data.model.UserResponse
import com.kim344.domain.search.*

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

fun mapperToUser(userResponse: UserResponse): User {
    return User(
        userResponse.avatar_url,
        userResponse.bio,
        userResponse.blog,
        userResponse.company,
        userResponse.created_at,
        userResponse.email,
        userResponse.events_url,
        userResponse.followers,
        userResponse.followers_url,
        userResponse.following,
        userResponse.following_url,
        userResponse.gists_url,
        userResponse.gravatar_id,
        userResponse.hireable,
        userResponse.html_url,
        userResponse.id,
        userResponse.location,
        userResponse.login,
        userResponse.name,
        userResponse.node_id,
        userResponse.organizations_url,
        userResponse.public_gists,
        userResponse.public_repos,
        userResponse.received_events_url,
        userResponse.repos_url,
        userResponse.site_admin,
        userResponse.starred_url,
        userResponse.subscriptions_url,
        userResponse.twitter_username,
        userResponse.type,
        userResponse.updated_at,
        userResponse.url
    )
}

fun mapperToRandomUser(randomUserResponse: RandomUserResponse): RandomUser {
    return RandomUser(
        randomUserResponse.results.toList().map {
            Result(
                it.email,
                Location(
                    it.location.country
                ),
                Login(
                    it.login.username
                ),
                Picture(
                    it.picture.thumbnail
                ),
                ID(
                    it.id.name
                )
            )
        }
    )
}