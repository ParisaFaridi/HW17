package com.example.homework17.ui.homefragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework17.data.Repository
import com.example.homework17.data.datasources.RemoteDataSource
import com.example.homework17.data.model.Movie
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository) : ViewModel() {

    val status = MutableLiveData<ApiStatus>()
    val movies = MutableLiveData<List<Movie>>()

    init {
        getPopularMovie()
    }

    private fun getPopularMovie() {
        viewModelScope.launch {
            status.value = ApiStatus.LOADING
            movies.value = repository.getPopular()
            if (movies.value == null)
                status.value = ApiStatus.ERROR
            else
                status.value = ApiStatus.DONE
        }
    }
}

enum class ApiStatus { LOADING, ERROR, DONE }