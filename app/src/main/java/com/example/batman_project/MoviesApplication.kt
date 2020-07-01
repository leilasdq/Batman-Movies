package com.example.batman_project

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.example.batman_project.database.ObjectBox


class MoviesApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        ObjectBox.init(this)
    }

    companion object {
        lateinit var instance: MoviesApplication
    }

    fun hasNetwork(): Boolean {
        return isNetworkConnected()
    }

    private fun isNetworkConnected(): Boolean {
        val cm =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting
    }
}