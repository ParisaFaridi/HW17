package com.example.homework17.ui.upcomingfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework17.data.Repository
import com.example.homework17.data.datasources.RemoteDataSource
import com.example.homework17.data.model.Movie
import com.example.homework17.ui.homefragment.ApiStatus
import kotlinx.coroutines.launch

class UpComingViewModel (private val repository: Repository) : ViewModel() {

    private val status = MutableLiveData<ApiStatus>()
    val upComingMovies = MutableLiveData<List<Movie>>()


    init {
        getUpComings()
    }
    private fun getUpComings(){
        viewModelScope.launch {
            status.value = ApiStatus.LOADING
            upComingMovies.value = repository.getUpComings()
            status.value = ApiStatus.DONE
        }
    }
}