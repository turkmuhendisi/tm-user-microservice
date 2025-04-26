package com.turkmuhendisi.user.dto

import com.turkmuhendisi.user.enums.Category

data class ArticleCreationDto(
    val title: String,
    val content: String,
    val imageUrl: String,
    val author: String,
    val comments: MutableList<String>,
    val category: MutableSet<Category>
)
