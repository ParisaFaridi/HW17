package com.example.homework17.ui.upcomingfragment

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.*
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.homework17.App
import com.example.homework17.Resource
import com.example.homework17.data.Repository
import com.example.homework17.data.model.Movie
import com.example.homework17.data.model.MovieList
import kotlinx.coroutines.launch
import retrofit2.Response

class UpComingViewModel (private val repository: Repository,app: Application) : AndroidViewModel(app) {

    val upComingMovies = MutableLiveData<Resource<List<Movie>>>()

    init {
        getUpComings()
    }

    private fun getUpComings() = viewModelScope.launch {
        upComingMovies.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()) {
                val response = repository.getUpComings()
                upComingMovies.postValue(handleUpcomingMovies(response))
            } else {
                upComingMovies.postValue(Resource.Success(repository.getUpcomingFromDb()))
            }
        } catch (t: Throwable) {
            upComingMovies.postValue(Resource.Error("Unknown Error!"))
        }
    }

    private fun handleUpcomingMovies(response: Response<MovieList>): Resource<List<Movie>>{
            if (response.isSuccessful){
                response.body()?.let{
                    return Resource.Success(it.results)
                }
            }
            return Resource.Error(response.message())
    }
    private fun hasInternetConnection():Boolean{
        val connectivityManager = getApplication<App>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val activeNetwork = connectivityManager.activeNetwork ?: return  false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?:return false
            return when {
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }else{
            connectivityManager.activeNetworkInfo?.run{
                return when(type){
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE ->true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else ->false
                }
            }
        }
        return false
    }

}