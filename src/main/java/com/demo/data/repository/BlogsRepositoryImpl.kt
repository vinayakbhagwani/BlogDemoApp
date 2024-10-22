package com.demo.data.repository

import com.demo.data.mappers.toDomain
import com.demo.data.network.remote.ApiService
import com.demo.data.network.utils.SafeApiRequest
import com.demo.domain.model.Blog
import com.demo.domain.repository.BlogsRepository
import javax.inject.Inject

class BlogsRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): BlogsRepository, SafeApiRequest() {

    override suspend fun getBlogs(
        limit: Int, skip: Int
    ): List<Blog> {
        val blogDto = safeApiRequest { apiService.getBlogs(limit, skip) }
        return blogDto.posts?.toDomain() ?: emptyList()
    }

}