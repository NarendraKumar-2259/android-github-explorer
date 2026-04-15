package io.github.narendrakumar2259.githubexplorer

import io.github.narendrakumar2259.githubexplorer.domain.usecase.GetUserUseCase
import junit.framework.TestCase.assertTrue
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlinx.coroutines.test.runTest

class GetUserUseCaseTest {

    @Test
    fun `when repository returns success, usecase returns success`() = runTest {
        val fakeRepository = FakeUserRepository(shouldReturnError = false)
        val useCase = GetUserUseCase(fakeRepository)

        val result = useCase("testuser")

        assertTrue(result.isSuccess)
        assertEquals("Test User", result.getOrNull()?.name)
    }

    @Test
    fun `when repository returns error, usecase returns failure`() = runTest {
        val fakeRepository = FakeUserRepository(shouldReturnError = true)
        val useCase = GetUserUseCase(fakeRepository)

        val result = useCase("testuser")

        assertTrue(result.isFailure)
        assertEquals("User not found", result.exceptionOrNull()?.message)
    }
}