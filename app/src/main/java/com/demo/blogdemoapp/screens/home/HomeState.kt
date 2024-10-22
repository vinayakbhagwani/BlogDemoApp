package com.demo.blogdemoapp.screens.home

import com.demo.domain.model.Blog

data class HomeState(
    var isLoading: Boolean,
    var data: List<Blog>?,
    var error: String
) {
    constructor() : this(isLoading = false, data = null, error = "")
}