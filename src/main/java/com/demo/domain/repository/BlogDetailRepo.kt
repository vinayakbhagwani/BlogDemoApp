package com.demo.domain.repository

import com.demo.domain.model.Blog

interface BlogDetailRepo {

    suspend fun getBlogDetails(id: Int): Blog
}