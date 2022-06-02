package com.example.homework17.ui.searchfragment

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
class SearchViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val searchResults = MutableLiveData<List<Movie>>()

    fun search(query: String){
        viewModelScope.launch {
            searchResults.value = repository.search(query)
        }
    }
}