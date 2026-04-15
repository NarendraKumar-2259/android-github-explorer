package io.github.narendrakumar2259.githubexplorer

import io.github.narendrakumar2259.githubexplorer.domain.repository.UserRepository

class FakeUserRepository(
    private val shouldReturnError: Boolean = false
): UserRepository {
    override suspend fun getUserDetails(username: String): Result<io.github.narendrakumar2259.githubexplorer.domain.model.UserDetails> {
        return if (shouldReturnError) {
            Result.failure(Exception("User not found"))
        } else {
            Result.success(
                io.github.narendrakumar2259.githubexplorer.domain.model.UserDetails(
                    login = "testuser",
                    name = "Test User",
                    avatarUrl = "https://example.com/avatar.png",
                    bio = "This is a test user.",
                    location = "Test City",
                    followers = 10,
                    following = 5,
                    publicRepos = 3
                )
            )
        }
    }
}