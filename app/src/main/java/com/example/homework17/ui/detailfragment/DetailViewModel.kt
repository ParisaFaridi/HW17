package com.example.homework17.ui.detailfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework17.data.Repository
import com.example.homework17.data.model.Movie
import com.example.homework17.ui.homefragment.ApiStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: Repository):ViewModel() {

    private val status = MutableLiveData<ApiStatus>()
    val movie = MutableLiveData<Movie>()

   fun getMovie(id :Int) {
        viewModelScope.launch {
            status.value = ApiStatus.LOADING
            movie.value = repository.getMovieDetail(id)
            status.value = ApiStatus.DONE
        }
    }
}