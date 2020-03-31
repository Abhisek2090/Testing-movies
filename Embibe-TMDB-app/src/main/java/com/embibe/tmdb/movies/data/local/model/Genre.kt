package com.embibe.tmdb.movies.data.local.model

import com.google.gson.annotations.SerializedName

/**
 * @author Abhisek
*/
data class Genre (

    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("name")
    var name: String? = null
)
