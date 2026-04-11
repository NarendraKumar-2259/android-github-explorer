package io.github.narendrakumar2259.githubexplorer.data.remote

data class UserResponse(
    val login: String,
    val name: String?,
    val avatar_url: String?,
    val bio: String?,
    val location: String?,
    val followers: Int,
    val following: Int,
    val public_repos: Int
)