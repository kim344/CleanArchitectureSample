package com.kim344.domain.usecase

import com.kim344.domain.repository.LoginRepository
import javax.inject.Inject

class GetLoginUseCase @Inject constructor(private val repository: LoginRepository) {
    fun execute(): Boolean = repository.autoLogin
}