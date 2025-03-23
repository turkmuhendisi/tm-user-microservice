package com.turkmuhendisi.user.dto

import com.turkmuhendisi.user.enums.Role

data class UserResponse(
    var email: String,
    var password: String,
    var role: MutableSet<Role>
)
