package com.example.homework17.ui.searchfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework17.data.Repository
import com.example.homework17.data.model.Movie
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: Repository) : ViewModel() {

    val searchResults = MutableLiveData<List<Movie>>()

    fun search(query: String){
        viewModelScope.launch {
            searchResults.value = repository.search(query)
        }
    }
}