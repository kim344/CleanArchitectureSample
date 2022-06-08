package com.kim344.data.repository.user.remote

import com.kim344.data.api.UserApiInterface
import com.kim344.data.model.UserResponse
import io.reactivex.Single
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(private val userApiInterface: UserApiInterface): UserRemoteDatasource {
    override fun getUserData(userId: String): Single<UserResponse> {
        return userApiInterface.getUserData(userId)
    }
}