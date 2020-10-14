package com.example.myfirstprograminkotlin.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Episode {
    @SerializedName("info")
    @Expose
    var info: Info? = null

    @SerializedName("results")
    @Expose
    var results: List<ResultEpisode>? = null

}