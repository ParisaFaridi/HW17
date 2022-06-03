package com.example.homework17.ui.homefragment

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

class HomeViewModel(private val repository: Repository,app: Application) : AndroidViewModel(app) {

    val movies :MutableLiveData<Resource<List<Movie>>> = MutableLiveData()

    init {
        getPopularMovie()
    }

    private fun getPopularMovie() = viewModelScope.launch {
        movies.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()){
                val response  = repository.getPopular()
                movies.postValue(handlePopularMovies(response))
            }else{
                movies.postValue(Resource.Success(repository.getPopularFromDb()))
            }
        }catch (t :Throwable){
            movies.postValue(Resource.Error("Unknown Error!"))
        }
    }
    private fun handlePopularMovies(response: Response<MovieList>):Resource<List<Movie>>{
        if (response.isSuccessful){
            response.body()?.let{
                return Resource.Success(it.results)
            }
        }
        return Resource.Error(response.message())
    }
}