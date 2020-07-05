package com.example.batman_project.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.batman_project.database.DatabaseRepository
import com.example.batman_project.MoviesApplication
import com.example.batman_project.model.Search
import com.example.batman_project.network.FetchItems

class MoviesViewModel: ViewModel() {
    private val onSearch = MutableLiveData<Search>()
    val search: LiveData<Search>
        get() = onSearch

    init {
        onSearch.value = null
    }

    fun getAllMovies(): LiveData<List<Search>>{
        return if (MoviesApplication.instance.hasNetwork()) {
            FetchItems.getAllMovies()
        } else {
            getFromDatabase()
        }
    }

    fun onItemClicked(search: Search){
        onSearch.value = search
        onSearch.value = null
    }

    fun navigateFinished(){
        onSearch.value = null
        getAllMovies()
    }

    private fun getFromDatabase(): MutableLiveData<List<Search>> {
        return DatabaseRepository.getAllMovies()
    }

    fun cacheItems(list: List<Search>){
        DatabaseRepository.removeAllMovies()
        DatabaseRepository.saveToMovieDatabase(list)
    }
}