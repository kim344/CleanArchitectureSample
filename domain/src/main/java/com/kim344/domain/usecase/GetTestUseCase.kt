package com.kim344.domain.usecase

import com.kim344.domain.repository.TestRepository
import javax.inject.Inject

class GetTestUseCase @Inject constructor(private val testRepository: TestRepository) {
    fun execute(): String{
        return testRepository.testValue
    }
}