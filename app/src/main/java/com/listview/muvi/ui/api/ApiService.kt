package com.listview.muvi.ui.api

import com.listview.muvi.BuildConfig
import com.listview.muvi.ui.api.response.MovieResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing")
    fun getNowPlaying(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ) : Call<MovieResponse>

    @GET("movie/popular")
    fun getPopuler(
        @Query("api_key") apiKey : String = BuildConfig.API_KEY
    ) : Call<MovieResponse>

    @GET("movie/top_rated")
    fun getTopRated(
        @Query("api_key") ApiKey: String = BuildConfig.API_KEY
    ) : Call<MovieResponse>

    @GET("movie/upcoming")
    fun getUpcoming(
        @Query("api_key") ApiKey: String = BuildConfig.API_KEY
    ) : Call<MovieResponse>
}