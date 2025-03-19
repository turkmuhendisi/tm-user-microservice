package com.turkmuhendisi.user.model

import jakarta.persistence.*

@Entity
@Table(name = "\"user\"")
class User() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
    var username: String = ""
    var email: String = ""
    var password: String = ""
}