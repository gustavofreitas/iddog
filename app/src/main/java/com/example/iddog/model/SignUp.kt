package com.example.iddog.model

data class SignUpRequest(val email: String)

data class SignUpResponse(val user: User)