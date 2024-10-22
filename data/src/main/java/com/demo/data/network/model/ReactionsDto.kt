package com.demo.data.network.model


import com.google.gson.annotations.SerializedName

data class ReactionsDto(
    @SerializedName("dislikes") val dislikes: Int?,
    @SerializedName("likes") val likes: Int?
)