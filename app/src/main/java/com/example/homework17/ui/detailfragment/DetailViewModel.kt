package com.example.homework17.ui.detailfragment

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.homework17.App
import com.example.homework17.Resource
import com.example.homework17.data.Repository
import com.example.homework17.data.model.Movie
import kotlinx.coroutines.launch
import retrofit2.Response

class DetailViewModel(private val repository: Repository,app: Application) : AndroidViewModel(app){

    val movie = MutableLiveData<Resource<Movie>>()

   fun getMovie(id :Int) = viewModelScope.launch {

       movie.postValue(Resource.Loading())
       try {
           if (hasInternetConnection()){
               val response  = repository.getMovieDetail(id)
               movie.postValue(handleMovie(response))
           }else{
               movie.postValue(Resource.Success(repository.getMovieDetailFromDb(id)))
           }
       }catch (t :Throwable){
           movie.postValue(Resource.Error("Unknown Error!"))
       }
   }
    private fun handleMovie(response: Response<Movie>): Resource<Movie> {
        if (response.isSuccessful){
            response.body()?.let{
                return Resource.Success(it)
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
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
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