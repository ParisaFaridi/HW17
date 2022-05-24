package com.example.homework17.domain


import com.example.homework17.data.datasources.RemoteDataSource
import com.example.homework17.network.ApiService
import com.example.homework17.ui.detailfragment.DetailViewModel
import com.example.homework17.ui.homefragment.HomeViewModel
import com.example.homework17.ui.upcomingfragment.UpComingViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://api.themoviedb.org/3/"

val appModule = module {

    single {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        moshi
    }

    single {
        val moshi = get() as Moshi
        val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL).build()
        retrofit
    }

    single {
        val retrofit = get() as Retrofit
        val apiService = retrofit.create(ApiService::class.java)
        apiService
    }
    single {
        val remoteDataSource = RemoteDataSource(get())
        remoteDataSource
    }

    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { UpComingViewModel(get()) }
}