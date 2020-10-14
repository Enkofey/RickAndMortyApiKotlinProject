package com.example.myfirstprograminkotlin.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class ResultEpisode {
    @SerializedName("id")
    @Expose
    var id: Int = 0

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("air_date")
    @Expose
    var airDate: String? = null

    @SerializedName("episode")
    @Expose
    var episode: String? = null

    @SerializedName("characters")
    @Expose
    var characters: List<String>? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("created")
    @Expose
    var created: String? = null

}
