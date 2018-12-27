package com.boolenull.booleanhub.model

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("type") val type : String,
    @SerializedName("created_at") val date : String)