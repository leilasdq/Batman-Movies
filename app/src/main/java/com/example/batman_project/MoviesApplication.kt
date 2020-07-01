package com.example.batman_project

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager


class MoviesApplication: Application() {

    //private lateinit var instance: MoviesApplication

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: MoviesApplication
            private set
    }

    fun hasNetwork(): Boolean {
        return instance.isNetworkConnected()
    }

    private fun isNetworkConnected(): Boolean {
        val cm =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting
    }
}