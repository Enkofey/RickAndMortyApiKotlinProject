package com.example.myfirstprograminkotlin.models.webservices

import com.example.myfirstprograminkotlin.models.Episode
import com.example.myfirstprograminkotlin.models.ResultEpisode
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodeEndpoint {
    @GET("episode/")
    fun getEpisode(): Call<Episode>
    @GET("episode/{id}")
    fun getEpisodeDetail(@Path("id") id :Int?):Call<ResultEpisode>
}