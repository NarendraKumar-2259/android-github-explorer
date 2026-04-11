package io.github.narendrakumar2259.githubexplorer.data.local

import javax.inject.Inject

class UserLocalDataSource @Inject constructor(
    private val userDao: UserDao
){
    suspend fun getUser(username: String): UserEntity? {
        return userDao.getUser(username)
    }

    suspend fun insertUser(user: UserEntity) {
        userDao.insertUser(user)
    }

}