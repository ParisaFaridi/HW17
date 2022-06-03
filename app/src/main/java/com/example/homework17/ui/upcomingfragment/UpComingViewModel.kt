package com.example.homework17.ui.upcomingfragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.homework17.Resource
import com.example.homework17.data.Repository
import com.example.homework17.data.model.Movie
import com.example.homework17.data.model.MovieList
import com.example.homework17.hasInternetConnection
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
            val response = repository.getUpcoming(hasInternetConnection())
            upComingMovies.postValue(handleUpcomingMovies(response))
            }catch (t: Throwable) {
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
}