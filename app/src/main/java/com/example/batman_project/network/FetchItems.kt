package com.example.batman_project.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.batman_project.MoviesApplication
import com.example.batman_project.model.Detail
import com.example.batman_project.model.Movies
import com.example.batman_project.model.Search
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit


object FetchItems {

    private const val BASE_URL = "http://www.omdbapi.com/"
    private const val API_KEY = "3e974fca"
    private const val TYPE = "batman"
    private const val cacheSize = (5 * 1024 * 1024).toLong()
    val myCache = Cache(MoviesApplication.instance.cacheDir, cacheSize)

    val okHttpClient = OkHttpClient.Builder()
        .cache(myCache)
        .addInterceptor { chain ->
            var request = chain.request()
            request = if (MoviesApplication.instance.hasNetwork())
                request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
            else
                request.newBuilder().header(
                    "Cache-Control",
                    "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                ).build()
            chain.proceed(request)
        }
        .build()
    private var mRetrofit: Retrofit
    private var mApiService: ApiService
    private val mQueries = HashMap<String, String>()

    init {
        mQueries["apikey"] =
            API_KEY
        mQueries["s"] =
            TYPE

        mRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        mApiService = mRetrofit.create(ApiService::class.java)
    }

    fun getAllMovies(): MutableLiveData<List<Search>> {
        val call = mApiService.getAllMovies(mQueries)
        val data = MutableLiveData<List<Search>>()

        call.enqueue(object : Callback<Movies> {
            override fun onFailure(call: Call<Movies>, t: Throwable) {
                Log.e("fetchhhhhhhhhhhhh", "Error: ${t.message}")
            }

            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                data.value = response.body()?.Search
            }
        })
        return data
    }

    fun getSpecificMovie(midbId: String): MutableLiveData<Detail> {
        val editedQuery = mQueries
        editedQuery.remove("s")
        editedQuery["i"] = midbId

        val call = mApiService.getDetail(editedQuery)
        val data = MutableLiveData<Detail>()

        call.enqueue(object : Callback<Detail> {
            override fun onFailure(call: Call<Detail>, t: Throwable) {
                Log.e("fetchhhhhhhhhhhhh", "Error: ${t.message}")
            }

            override fun onResponse(call: Call<Detail>, response: Response<Detail>) {
                data.value = response.body()
            }
        })
        return data
    }
}
