package io.github.narendrakumar2259.githubexplorer.domain.repository

import io.github.narendrakumar2259.githubexplorer.domain.model.UserDetails

interface UserRepository {

    suspend fun getUserDetails(username: String): Result<UserDetails>

}