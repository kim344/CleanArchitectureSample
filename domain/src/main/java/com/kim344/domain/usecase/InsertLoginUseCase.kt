package com.kim344.domain.usecase

import com.kim344.domain.repository.LoginRepository
import javax.inject.Inject

class InsertLoginUseCase @Inject constructor(private val repository: LoginRepository) {
    fun execute(success: Boolean) { // 다음 앱 실행부터는 자동로그인되게끔 플래그 저장
        repository.autoLogin = success
    }
}