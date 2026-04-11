package io.github.narendrakumar2259.githubexplorer.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val login: String,
    val name: String?,
    val avatarUrl: String?,
    val bio: String?,
    val location: String?,
    val followers: Int,
    val following: Int,
    val publicRepos: Int,
    val lastUpdated: Long = System.currentTimeMillis()
)