package com.kim344.data.repository.search

import com.kim344.data.mapper.mapperToMovie
import com.kim344.data.repository.search.local.MovieLocalDataSource
import com.kim344.data.repository.search.remote.MovieRemoteDatasource
import com.kim344.data.utils.LAST_PAGE
import com.kim344.data.utils.NO_DATA_FROM_LOCAL_DB
import com.kim344.domain.repository.MovieRepository
import com.kim344.domain.search.Movie
import io.reactivex.Flowable
import io.reactivex.Single
import java.lang.IllegalStateException
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieRemoteDatasource: MovieRemoteDatasource,
    private val movieLocalDataSource: MovieLocalDataSource
): MovieRepository {

    // 첫 영화검색
    override fun getSearchMovies(query: String): Flowable<List<Movie>> {
        return movieLocalDataSource.getSearchMovies(query)
            .onErrorReturn { listOf() }
            .flatMapPublisher { localMovies ->
                if (localMovies.isEmpty()){
                    getRemoteSearchMovies(query)
                        .toFlowable()
                        .onErrorReturn { listOf() }
                } else {
                    val local = Single.just(mapperToMovie(localMovies))
                    val remote = getRemoteSearchMovies(query)
                        .onErrorResumeNext { local }
                    Single.concat(local, remote)
                }
            }
    }

    // 인터넷 끓긴 경우 로컬디비 검색
    override fun getLocalSearchMovies(query: String): Flowable<List<Movie>> {
        return movieLocalDataSource.getSearchMovies(query)
            .onErrorReturn { listOf() }
            .flatMapPublisher { cacheMovies ->
                if (cacheMovies.isEmpty()){
                    Flowable.error(IllegalStateException(NO_DATA_FROM_LOCAL_DB))
                } else {
                    Flowable.just(mapperToMovie(cacheMovies))
                }
            }
    }

    // 서버 DB 영화검색 요청
    override fun getRemoteSearchMovies(query: String): Single<List<Movie>> {
        return movieRemoteDatasource.getSearchMovies(query)
            .flatMap {
                movieLocalDataSource.insertMovie(it.movies)
                    .andThen(Single.just(mapperToMovie(it.movies)))
            }
    }

    // 영화 검색 후 스크롤 영화 더 불로오기
    override fun getPagingMovies(query: String, offset: Int): Single<List<Movie>> {
        return movieRemoteDatasource.getSearchMovies(query, offset)
            .flatMap {
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
            }
    }
}