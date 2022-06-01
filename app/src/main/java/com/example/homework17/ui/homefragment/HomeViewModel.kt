package com.example.homework17.ui.homefragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework17.Resource
import com.example.homework17.data.Repository
import com.example.homework17.data.model.MovieList
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel(private val repository: Repository) : ViewModel() {

    val movies :MutableLiveData<Resource<MovieList>> = MutableLiveData()

    init {
        getPopularMovie()
    }

    private fun getPopularMovie() = viewModelScope.launch {
        movies.postValue(Resource.Loading())
        val response  = repository.getPopular()
        movies.postValue(handlePopularMovies(response))
    }
    private fun handlePopularMovies(response: Response<MovieList>):Resource<MovieList>{
        if (response.isSuccessful){
            response.body()?.let{
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }
}

enum class ApiStatus { LOADING, ERROR, DONE }