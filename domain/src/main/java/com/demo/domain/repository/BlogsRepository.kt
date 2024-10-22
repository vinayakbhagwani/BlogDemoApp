package com.demo.domain.repository

import com.demo.domain.model.Blog

interface BlogsRepository {

    suspend fun getBlogs(limit: Int, skip: Int) : List<Blog>
}