package io.github.narendrakumar2259.githubexplorer.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.narendrakumar2259.githubexplorer.data.local.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM users WHERE login = :username")
    suspend fun getUser(username: String): UserEntity?

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertUser(user: UserEntity)
}