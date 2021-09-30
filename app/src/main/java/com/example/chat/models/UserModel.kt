package com.example.chat.models

data class UserModel(
    val id: String = "",
    var username: String = "",
    var bio: String = "",
    var fullname: String = "",
    var phone: String = "",
    var state: String = "",
    var photoUrl: String = "empty"
)