package com.kim344.domain.test

import javax.inject.Inject

class TestRepositoryImpl @Inject constructor(private var str: String): TestRepository {
    override var value: String
        get() = str
        set(value) {
            str = value
        }
}