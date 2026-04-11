package io.github.narendrakumar2259.githubexplorer.data.mapper


import io.github.narendrakumar2259.githubexplorer.data.local.UserEntity
import io.github.narendrakumar2259.githubexplorer.data.remote.UserResponse
import io.github.narendrakumar2259.githubexplorer.domain.model.UserDetails

fun UserResponse.toDomain(): UserDetails {
    return UserDetails(
        name = name ?: "N/A",
        avatarUrl = avatar_url ?: "N/A",
        bio = bio ?: "N/A",
        location = location ?: "N/A",
        followers = followers,
        following = following,
        publicRepos = public_repos
    )

}


fun UserResponse.toEntity(): UserEntity {
    return UserEntity(
        login = login,
        name = name ?: "N/A",
        avatarUrl = avatar_url ?: "N/A",
        bio = bio ?: "N/A",
        location = location ?: "N/A",
        followers = followers,
        following = following,
        publicRepos = public_repos
    )
}