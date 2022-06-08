package com.kim344.data.repository.user.remote

import com.kim344.data.model.UserResponse
import io.reactivex.Single

interface UserRemoteDatasource {

    fun getUserData(
        userId: String
    ): Single<UserResponse>
}