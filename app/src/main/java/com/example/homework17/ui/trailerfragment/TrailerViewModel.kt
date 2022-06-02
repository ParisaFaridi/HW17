package com.example.homework17.ui.trailerfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework17.data.Repository
import kotlinx.coroutines.launch

class TrailerViewModel(private val repository: Repository): ViewModel() {

    val trailer = MutableLiveData<String?>()

    fun getTrailer(id:Int) = viewModelScope.launch {
        try {
            trailer.value = "https://www.youtube.com/watch?v=" + (repository.getTrailer(id).key)
        }catch (e:Exception){
            trailer.value = null
        }
    }
}