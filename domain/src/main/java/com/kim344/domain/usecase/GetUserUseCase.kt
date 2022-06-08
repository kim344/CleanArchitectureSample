package com.kim344.domain.usecase

import com.kim344.domain.repository.UserRepository
import com.kim344.domain.search.User
import io.reactivex.Single
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    fun execute(
        userId: String
    ): Single<User> = userRepository.getUserData(userId)
}