package com.example.homework17.ui.detailfragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.homework17.Resource
import com.example.homework17.data.Repository
import com.example.homework17.data.model.Movie
import com.example.homework17.hasInternetConnection
import kotlinx.coroutines.launch
import retrofit2.Response

class DetailViewModel(private val repository: Repository,app: Application) : AndroidViewModel(app){

    val movie = MutableLiveData<Resource<Movie>>()

   fun getMovie(id :Int) = viewModelScope.launch {

       movie.postValue(Resource.Loading())
       try {
           if (hasInternetConnection()){
               val response  = repository.getMovieDetail(id)
               movie.postValue(handleMovie(response))
           }else{
               movie.postValue(Resource.Success(repository.getMovieDetailFromDb(id)))
           }
       }catch (t :Throwable){
           movie.postValue(Resource.Error("Unknown Error!"))
       }
   }
    private fun handleMovie(response: Response<Movie>): Resource<Movie> {
        if (response.isSuccessful){
            response.body()?.let{
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }
}