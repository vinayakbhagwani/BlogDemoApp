package com.demo.domain.model

data class Blogs(
    val limit: Int,
    val posts: List<Blog>,
    val skip: Int,
    val total: Int
)
