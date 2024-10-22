package com.demo.data.repository

import com.demo.data.network.remote.ApiService
import com.demo.data.network.utils.SafeApiRequest
import com.demo.domain.model.Blog
import com.demo.domain.repository.BlogDetailRepo
import javax.inject.Inject

class BlogDetailRepoImpl @Inject constructor(private val apiService: ApiService): BlogDetailRepo, SafeApiRequest() {

    override suspend fun getBlogDetails(id: Int): Blog {
        val response = safeApiRequest { apiService.getBlogDetail(id = id) }

        val blog = Blog(
            body = response.body ?: "",
            id = response.id ?: 0,
//            reactions = it.reactions?.toDomain() ?: Reactions(likes = 0, dislikes = 0),
//            tags = it.tags ?: emptyList(),
            title = response.title ?: "",
            userId = response.userId ?: 0,
            views = response.views ?: 0
        )

        return blog
    }
}