package com.demo.data.network.model


import com.google.gson.annotations.SerializedName

data class PostDto(
    @SerializedName("body") val body: String?,
    @SerializedName("id") val id: Int?,
    @SerializedName("reactions") val reactions: ReactionsDto?,
    @SerializedName("tags") val tags: List<String>?,
    @SerializedName("title") val title: String?,
    @SerializedName("userId") val userId: Int?,
    @SerializedName("views") val views: Int?
)