package com.example.myfirstprograminkotlin.models.webservices

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import com.example.myfirstprograminkotlin.models.Character

class CharacterService {
    val BASE_URL = "https://rickandmortyapi.com/api/"
    var gson : Gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssz").create()
    var retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build()

    fun  getCharacter() : CharacterEndpoint{
        return retrofit.create(CharacterEndpoint::class.java)
    }
}