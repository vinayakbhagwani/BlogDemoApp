package com.demo.data.mappers

import com.demo.data.network.model.PostDto
import com.demo.data.network.model.ReactionsDto
import com.demo.domain.model.Blog
import com.demo.domain.model.Reactions

fun List<PostDto>.toDomain(): List<Blog> {
    return map {
        Blog(
            body = it.body ?: "",
            id = it.id ?: 0,
//            reactions = it.reactions?.toDomain() ?: Reactions(likes = 0, dislikes = 0),
//            tags = it.tags ?: emptyList(),
            title = it.title ?: "",
            userId = it.userId ?: 0,
            views = it.views ?: 0
        )
    }
}

fun ReactionsDto.toDomain(): Reactions {
    return Reactions(
        likes = likes ?: 0,
        dislikes = dislikes ?: 0
    )
}