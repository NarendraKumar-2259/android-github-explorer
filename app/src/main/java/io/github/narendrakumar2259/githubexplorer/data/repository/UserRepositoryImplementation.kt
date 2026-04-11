package io.github.narendrakumar2259.githubexplorer.data.repository

import io.github.narendrakumar2259.githubexplorer.data.local.UserLocalDataSource
import io.github.narendrakumar2259.githubexplorer.data.mapper.toDomain
import io.github.narendrakumar2259.githubexplorer.data.mapper.toEntity
import io.github.narendrakumar2259.githubexplorer.data.remote.UserRemoteDataSource
import io.github.narendrakumar2259.githubexplorer.domain.model.UserDetails
import io.github.narendrakumar2259.githubexplorer.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImplementation @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserLocalDataSource
): UserRepository {
    override suspend fun getUserDetails(username: String): Result<UserDetails> {
        return try {
            val cachedUser = localDataSource.getUser(username)
            if (cachedUser != null && isCacheValid(cachedUser.lastUpdated)) {
                Result.success(cachedUser.toDomain())
            } else {
                val userResponse = remoteDataSource.getUserDetails(username)
                val userEntity = userResponse.toEntity()
                localDataSource.insertUser(userEntity)
                Result.success(userResponse.toDomain())
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun isCacheValid(lastUpdated: Long): Boolean {
        return System.currentTimeMillis() - lastUpdated < 60 * 60 * 1000
    }
}