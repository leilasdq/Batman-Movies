package com.example.batman_project.database

import androidx.lifecycle.MutableLiveData
import com.example.batman_project.model.Detail
import com.example.batman_project.model.Search
import io.objectbox.Box
import io.objectbox.kotlin.boxFor

object DatabaseRepository {
    private val movieBox: Box<Search> = ObjectBox.boxStore.boxFor()
    private val detailBox: Box<Detail> = ObjectBox.boxStore.boxFor()

    fun getAllMovies(): MutableLiveData<List<Search>> {
        val list = movieBox.all
        val item = ArrayList<Search>()
        for (i in list) {
            item.add(i)
        }
        val data = MutableLiveData<List<Search>>()
        data.value = item

        return data
    }

    fun saveToMovieDatabase(list: List<Search>) {
        movieBox.put(list)
    }

    fun removeAllMovies() {
        movieBox.removeAll()
    }

    fun getSpecificMovie(imdbId: String): MutableLiveData<Detail> {
        val list = detailBox.all
        var id: Long = -1
        val liveData = MutableLiveData<Detail>()
        for (i in list) {
            if (imdbId == i.imdbID) {
                id = detailBox.getId(i)
            }
        }
        liveData.value = detailBox.get(id)
        return liveData
    }

    fun putSpecificMovieDetail(detail: Detail) {
        val list = detailBox.all
        for (i in list) {
            if (detail.imdbID != i.imdbID) {
                detailBox.put(detail)
            }
        }
    }
}