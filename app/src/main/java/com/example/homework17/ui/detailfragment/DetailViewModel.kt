package com.example.homework17.ui.detailfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework17.data.datasources.RemoteDataSource
import com.example.homework17.data.model.Detail
import com.example.homework17.data.model.Movie
import com.example.homework17.ui.homefragment.ApiStatus
import kotlinx.coroutines.launch

//add koin
class DetailViewModel(private val remoteDataSource: RemoteDataSource):ViewModel() {

    private val status = MutableLiveData<ApiStatus>()
    val movie = MutableLiveData<Detail>()
    val trailer =MutableLiveData<String>()

   fun getMovie(id :Int) {
        viewModelScope.launch {
            status.value = ApiStatus.LOADING
            movie.value = remoteDataSource.getMovieDetail(id)
            status.value = ApiStatus.DONE
        }
    }

    fun getTrailer(id:Int){
        viewModelScope.launch {
            trailer.value = "https://www.youtube.com/watch?v=" + remoteDataSource.getTrailer(id).key
        }
    }
}