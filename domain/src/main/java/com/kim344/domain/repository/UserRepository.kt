package com.kim344.domain.repository

import com.kim344.domain.search.RandomUser
import com.kim344.domain.search.User
import io.reactivex.Single

interface UserRepository {

    fun getUserData(
        userId: String
    ): Single<User>

    fun getRandomUserData(
        results: String
    ): Single<RandomUser>

}