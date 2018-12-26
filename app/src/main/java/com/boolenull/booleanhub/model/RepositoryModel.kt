package com.boolenull.booleanhub.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class RepositoryModel(
        @PrimaryKey(autoGenerate = true) var id : Int,
        @SerializedName("name") val title : String,
        @SerializedName("language") val language : String,
        @SerializedName("created_at") var date : String,
        @SerializedName("updated_at") var dateUpdate : String,
        @SerializedName("html_url") var link : String)