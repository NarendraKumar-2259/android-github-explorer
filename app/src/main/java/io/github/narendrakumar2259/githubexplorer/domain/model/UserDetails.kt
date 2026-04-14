package io.github.narendrakumar2259.githubexplorer.domain.model

data class UserDetails(
    val login: String,
    val name: String,
    val avatarUrl: String,
    val location: String,
    val bio: String,
    val followers: Int,
    val following: Int,
    val publicRepos: Int
)
