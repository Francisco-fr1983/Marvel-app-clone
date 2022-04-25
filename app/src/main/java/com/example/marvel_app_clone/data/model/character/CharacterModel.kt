package com.example.marvel_app_clone.data.model.character


import com.google.gson.annotations.SerializedName
import java.io.Serializable
import com.example.marvel_app_clone.data.model.ThumbnailModel

data class CharacterModel(

    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailModel
) : Serializable
