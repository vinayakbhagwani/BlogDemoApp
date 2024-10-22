package com.demo.data.network.model


import com.google.gson.annotations.SerializedName

data class BlogDto(
    @SerializedName("limit") val limit: Int?,
    @SerializedName("posts") val posts: List<PostDto>?,
    @SerializedName("skip") val skip: Int?,
    @SerializedName("total") val total: Int?
)