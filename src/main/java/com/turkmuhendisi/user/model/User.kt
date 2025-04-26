package com.turkmuhendisi.user.model

import com.turkmuhendisi.user.enums.Role
import jakarta.persistence.*

@Entity
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = 0,
    var username: String? = "",
    var email: String? = "",
    var password: String? = "",

    @ElementCollection(targetClass = Role::class, fetch = FetchType.EAGER)
    @JoinTable(name = "authorities", joinColumns = [JoinColumn(name = "user_id")])
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    val authorities: Set<Role>? = setOf()
) {
    constructor(
        username: String,
        email: String,
        password: String
    ) : this(
        null,
        username,
        email,
        password,
        authorities = setOf(Role.ROLE_USER)
    )
}
