package com.example.homework17.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework17.data.datasources.RemoteDataSource
import kotlinx.coroutines.launch

class MovieViewModel(val remoteDataSource: RemoteDataSource) : ViewModel() {

    val moviewO = MutableLiveData("not")
    fun getPopularMovie(){
        viewModelScope.launch {
            val list = remoteDataSource.getPopular()
            moviewO.value = list[0].overview
        }
    }
}