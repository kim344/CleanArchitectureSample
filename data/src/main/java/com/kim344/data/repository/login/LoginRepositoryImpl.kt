package com.kim344.data.repository.login

import com.kim344.data.repository.login.local.LoginLocalDataSource
import com.kim344.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val loginLocalDataSource: LoginLocalDataSource): LoginRepository {

    override var autoLogin: Boolean
        get() = loginLocalDataSource.autoLogin
        set(value) {
            loginLocalDataSource.autoLogin = value
        }
}