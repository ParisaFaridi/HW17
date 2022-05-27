package com.example.homework17.ui.trailerfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework17.data.Repository
import com.example.homework17.data.datasources.RemoteDataSource
import kotlinx.coroutines.launch

class TrailerViewModel(private val repository: Repository): ViewModel() {

        val trailer = MutableLiveData<String>()

    fun getTrailer(id:Int){
        viewModelScope.launch {
           trailer.value = "https://www.youtube.com/watch?v=" + (repository.getTrailer(id)?.key)
        }
    }
}