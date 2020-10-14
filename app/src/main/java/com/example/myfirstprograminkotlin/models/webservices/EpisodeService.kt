package com.example.myfirstprograminkotlin.models.webservices

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EpisodeService {
    val BASE_URL = "https://rickandmortyapi.com/api/"
    var gson : Gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssz").create()
    var retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build()

    fun  getEpisode() : EpisodeEndpoint{
        return retrofit.create(EpisodeEndpoint::class.java)
    }
}