package com.kim344.data.model

data class RandomUserResponse(
    val results: List<Result>
)

data class Result(
    val email: String,
    val location: Location,
    val login: Login,
    val picture: Picture,
    val id: ID
)

data class Location(
    val country: String
)

data class Login(
    val username: String
)

data class Picture(
    val thumbnail: String
)

data class ID(
    val name: String
)