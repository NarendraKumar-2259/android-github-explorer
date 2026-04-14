package io.github.narendrakumar2259.githubexplorer.data.mapper

import io.github.narendrakumar2259.githubexplorer.data.local.UserEntity
import io.github.narendrakumar2259.githubexplorer.domain.model.UserDetails

fun UserEntity.toDomain(): UserDetails {
    return UserDetails(
        login = login,
        name = name?: "N/A",
        avatarUrl = avatarUrl?: "N/A",
        bio = bio?: "N/A",
        location = location?: "N/A",
        followers = followers,
        following = following,
        publicRepos = publicRepos
    )
}