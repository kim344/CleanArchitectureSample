package com.kim344.domain.usecase

import com.kim344.domain.repository.LoginRepository
import javax.inject.Inject

class GetLoginUseCase @Inject constructor(private val repository: LoginRepository) {
    fun execute(): Boolean = repository.autoLogin //자동로그인 : 이전 로그인 기록있으면 자동로그인이 된다
}