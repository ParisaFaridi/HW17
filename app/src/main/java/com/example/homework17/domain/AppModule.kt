package com.example.homework17.domain

import android.content.Context
import androidx.room.Room
import com.example.homework17.network.ApiService
import com.example.homework17.room.MovieDatabase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://api.themoviedb.org/3/"

@Module
@InstallIn(SingletonComponent::class)
object AppModule{

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context):MovieDatabase {
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java, "movie_db"
        )
            .fallbackToDestructiveMigration().build()
    }
    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
    @Singleton
    @Provides
    fun provideMoshi():Moshi{
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }
    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient,moshi: Moshi):Retrofit{

        return Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL).client(client).build()
    }
    @Singleton
    @Provides
    fun provideInterceptor():OkHttpClient{
        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
        return OkHttpClient.Builder().addInterceptor(logger).build()
    }

}