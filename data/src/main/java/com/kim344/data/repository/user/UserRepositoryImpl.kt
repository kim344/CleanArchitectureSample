package com.kim344.data.repository.user

import com.kim344.data.mapper.mapperToRandomUser
import com.kim344.data.mapper.mapperToUser
import com.kim344.data.repository.user.remote.UserRemoteDatasource
import com.kim344.domain.repository.UserRepository
import com.kim344.domain.search.RandomUser
import com.kim344.domain.search.User
import io.reactivex.Single
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDatasource: UserRemoteDatasource
): UserRepository {
    override fun getUserData(userId: String): Single<User> {
        return userRemoteDatasource.getUserData(userId)
            .flatMap { userResponse ->
                Single.just(mapperToUser(userResponse))

                /*
                if (it.movies.isEmpty()){
                    Single.error(IllegalStateException(LAST_PAGE))
                } else {
                    if(offset != it.start){
                        Single.error(IllegalStateException(LAST_PAGE))
                    } else {
                        movieLocalDataSource.insertMovie(it.movies)
                            .andThen(Single.just(mapperToMovie(it.movies)))
                    }
                }
                */
            }
    }

    override fun getRandomUserData(results: String): Single<RandomUser> {
        return userRemoteDatasource.getRandomUserData(results)
            .flatMap { randomUserResponse ->
                // 여기 results Empty 일 때 예외 처리 해야함
                Single.just(mapperToRandomUser(randomUserResponse))
            }
    }
}