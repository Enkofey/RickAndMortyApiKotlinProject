package com.example.myfirstprograminkotlin.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Location {
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

}
