package io.github.narendrakumar2259.githubexplorer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.narendrakumar2259.githubexplorer.data.local.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}