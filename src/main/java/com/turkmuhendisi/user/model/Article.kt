package com.turkmuhendisi.user.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.turkmuhendisi.user.enums.Category
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Article(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = 0,
    var title: String = "",
    var content: String = "",
    var imageUrl: String = "",
    var author: String = "",

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    var created_at: LocalDateTime = LocalDateTime.now(),

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    var updated_at: LocalDateTime = LocalDateTime.now(),
    var like_status: Int = 0,

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "article_comments", joinColumns = [JoinColumn(name = "article_id")])
    @Column(name = "comment")
    var comments: MutableList<String> = mutableListOf(),

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "article_category", joinColumns = [JoinColumn(name = "article_id")])
    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    val category: MutableSet<Category>? = mutableSetOf()
) {
    constructor(
        title: String,
        content: String,
        imageUrl: String,
        author: String,
        created_at: LocalDateTime,
        updated_at: LocalDateTime,
        likeStatus: Int,
        comments: MutableList<String>,
        category: MutableSet<Category>
    ) : this(
        id = 0,
        title = title,
        content = content,
        imageUrl = imageUrl,
        author = author,
        created_at = created_at,
        updated_at = updated_at,
        like_status = likeStatus,
        comments = comments,
        category = category
    )
}
