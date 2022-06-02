package com.example.homework17.ui.trailerfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework17.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrailerViewModel @Inject constructor(private val repository: Repository): ViewModel() {

        val trailer = MutableLiveData<String>()

    fun getTrailer(id:Int){
        viewModelScope.launch {
           trailer.value = "https://www.youtube.com/watch?v=" + (repository.getTrailer(id)?.key)
        }
    }
}