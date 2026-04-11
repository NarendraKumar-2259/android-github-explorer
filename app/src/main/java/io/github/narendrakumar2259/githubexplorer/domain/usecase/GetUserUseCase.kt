package io.github.narendrakumar2259.githubexplorer.domain.usecase

import io.github.narendrakumar2259.githubexplorer.domain.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(username: String) = userRepository.getUserDetails(username)
}