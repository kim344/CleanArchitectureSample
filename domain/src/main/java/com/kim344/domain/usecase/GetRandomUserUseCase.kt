package com.kim344.domain.usecase

import com.kim344.domain.repository.UserRepository
import com.kim344.domain.search.RandomUser
import io.reactivex.Single
import javax.inject.Inject

class GetRandomUserUseCase @Inject constructor(private val userRepository: UserRepository) {

    fun getRandomUserData(
        results: String
    ): Single<RandomUser> = userRepository.getRandomUserData(results)

}