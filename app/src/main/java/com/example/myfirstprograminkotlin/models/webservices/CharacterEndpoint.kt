package com.example.myfirstprograminkotlin.models.webservices

import retrofit2.Call
import retrofit2.http.GET
import com.example.myfirstprograminkotlin.models.Character
import com.example.myfirstprograminkotlin.models.Result
import retrofit2.http.Path

interface CharacterEndpoint {
    @GET("character/")
    fun getCharacter(): Call<Character>

    @GET("character/{id}")
    fun getCharacterDetail(@Path("id") id :Int?):Call<Result>
}