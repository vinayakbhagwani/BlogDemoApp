package com.demo.data.network.remote

import com.demo.data.network.model.BlogDto
import com.demo.data.network.model.PostDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("posts")
    suspend fun getBlogs(
        @Query("limit") limit: Int,
        @Query("skip") skip: Int
    ) : Response<BlogDto>

    @GET("posts/{id}")
    suspend fun getBlogDetail(
        @Path("id") id: Int
    ) : Response<PostDto>

}