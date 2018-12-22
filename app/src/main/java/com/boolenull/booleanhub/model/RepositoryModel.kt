package com.boolenull.booleanhub.model

import com.google.gson.annotations.SerializedName

data class RepositoryModel(
    @SerializedName("name") val title : String,
    @SerializedName("language") val language : String,
    @SerializedName("created_at") val date : String,
    @SerializedName("updated_at") val dateUpdate : String)