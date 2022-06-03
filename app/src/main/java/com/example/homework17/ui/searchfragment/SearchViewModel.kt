package com.example.homework17.ui.searchfragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.homework17.data.Repository
import com.example.homework17.data.model.Movie
import com.example.homework17.hasInternetConnection
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: Repository,app: Application) : AndroidViewModel(app) {

    val searchResults = MutableLiveData<List<Movie>>()

    fun search(query: String){
        viewModelScope.launch {
            if (hasInternetConnection()){
                searchResults.value = repository.search(query,hasInternetConnection())
            }else{
                searchResults.value = repository.search("%$query%",hasInternetConnection())
            }
        }
    }
}