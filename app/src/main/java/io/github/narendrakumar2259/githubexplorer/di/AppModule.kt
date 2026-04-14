package io.github.narendrakumar2259.githubexplorer.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.narendrakumar2259.githubexplorer.data.local.UserDao
import io.github.narendrakumar2259.githubexplorer.data.local.UserDatabase
import io.github.narendrakumar2259.githubexplorer.data.local.UserLocalDataSource
import io.github.narendrakumar2259.githubexplorer.data.remote.UserRemoteDataSource
import io.github.narendrakumar2259.githubexplorer.data.remote.apiservice.GetUserApiService
import io.github.narendrakumar2259.githubexplorer.data.repository.UserRepositoryImplementation
import io.github.narendrakumar2259.githubexplorer.domain.repository.UserRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideGetUserApiService(retrofit: Retrofit): GetUserApiService {
        return retrofit.create(GetUserApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserDatabase(@ApplicationContext context: Context): UserDatabase {
        return Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            "user_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: UserDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        remoteDataSource: UserRemoteDataSource,
        localDataSource: UserLocalDataSource
    ): UserRepository {
        return UserRepositoryImplementation(remoteDataSource, localDataSource)
    }
}