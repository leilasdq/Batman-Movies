package com.example.batman_project.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
        return FetchItems.getAllMovies()
    }

    fun onItemClicked(search: Search){
        onSearch.value = search
        onSearch.value = null
    }

    fun navigateFinished(){
        getAllMovies()
    }
}