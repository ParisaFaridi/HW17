package com.example.homework17.domain



import androidx.room.Room
import com.example.homework17.data.Repository
import com.example.homework17.data.datasources.LocalDataSource
import com.example.homework17.data.datasources.RemoteDataSource
import com.example.homework17.network.ApiService
import com.example.homework17.room.MovieDatabase
import com.example.homework17.ui.detailfragment.DetailViewModel
import com.example.homework17.ui.homefragment.HomeViewModel
import com.example.homework17.ui.searchfragment.SearchViewModel
import com.example.homework17.ui.trailerfragment.TrailerViewModel
import com.example.homework17.ui.upcomingfragment.UpComingViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
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
        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
        val client = OkHttpClient.Builder().addInterceptor(logger).build()
        val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL).client(client).build()
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
    single {
        val localDataSource = LocalDataSource(get())
        localDataSource
    }
    single {
        val repository = Repository(get(), get())
        repository
    }

    single {
        val db = Room.databaseBuilder(androidApplication(), MovieDatabase::class.java, "movie_db")
            .fallbackToDestructiveMigration().build()
        db
    }

    single {
        val db = get() as MovieDatabase
        db.movieDao
    }

    viewModel { HomeViewModel(get(),androidApplication()) }
    viewModel { DetailViewModel(get()) }
    viewModel { UpComingViewModel(get(),androidApplication()) }
    viewModel { TrailerViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}