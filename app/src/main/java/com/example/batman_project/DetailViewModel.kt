package com.example.batman_project

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.batman_project.model.Detail
import com.example.batman_project.network.FetchItems

class DetailViewModel: ViewModel() {
    private val isShareClicked = MutableLiveData<Boolean>()
    val share: LiveData<Boolean>
        get() = isShareClicked
    private val isPlayClicked = MutableLiveData<Boolean>()
    val play: LiveData<Boolean>
        get() = isPlayClicked

    init {
        isPlayClicked.value = false
        isShareClicked.value = false
    }

    fun getSpecificMovie(id: String): LiveData<Detail>{
        return FetchItems.getSpecificMovie(id)
    }

    fun onShareClicked(){
        isShareClicked.value = true
    }

    fun onShareFinished(){
        isShareClicked.value = false
    }

    fun onPlayClicked(){
        isPlayClicked.value = true
    }

    fun onPlayFinished(){
        isPlayClicked.value = false
    }
}