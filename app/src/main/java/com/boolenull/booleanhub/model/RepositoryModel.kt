package com.boolenull.booleanhub.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class RepositoryModel(
    @PrimaryKey
    @SerializedName("id") var id : Int,
    @SerializedName("name") val title : String,
    @SerializedName("language") val language : String,
    @SerializedName("created_at") val date : String,
    @SerializedName("updated_at") val dateUpdate : String)