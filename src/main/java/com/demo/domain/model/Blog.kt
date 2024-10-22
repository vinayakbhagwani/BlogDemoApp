package com.demo.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Blog(
    val body: String,
    @PrimaryKey(autoGenerate = false) val id: Int,
//    val reactions: Reactions,
//    val tags: List<String>,
    val title: String,
    val userId: Int,
    val views: Int
)
