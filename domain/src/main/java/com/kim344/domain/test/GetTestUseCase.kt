package com.kim344.domain.test

import javax.inject.Inject

class GetTestUseCase @Inject constructor(private val testRepository: TestRepository) {
    fun setChangeValue(str: String){
        testRepository.value = str
    }
}