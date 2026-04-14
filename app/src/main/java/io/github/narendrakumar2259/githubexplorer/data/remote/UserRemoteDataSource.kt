package io.github.narendrakumar2259.githubexplorer.data.remote

import android.util.Log
import io.github.narendrakumar2259.githubexplorer.data.remote.apiservice.GetUserApiService
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val apiService: GetUserApiService
){
    suspend fun getUserDetails(username: String): UserResponse {
        Log.d("API_CALL", "Fetching user: $username")
        return apiService.getUserDetails(username)
    }

}