package com.example.homework17.ui.searchfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework17.data.Repository
import com.example.homework17.data.model.Movie
import com.example.homework17.ui.detailfragment.ApiStatus
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: Repository) : ViewModel() {

    private val status = MutableLiveData<ApiStatus>()
    val searchResults = MutableLiveData<List<Movie>>()

    fun search(query: String){
        viewModelScope.launch {
            searchResults.value = repository.search(query)
        }
    }
}