package io.github.narendrakumar2259.githubexplorer.data.remote.apiservice

import io.github.narendrakumar2259.githubexplorer.data.remote.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface GetUserApiService {

    @GET("users/{username}")
    suspend fun getUserDetails(@Path("username") username: String): UserResponse
}