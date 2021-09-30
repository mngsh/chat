package com.example.chat.utilits

import com.example.chat.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

lateinit var AUTH: FirebaseAuth
lateinit var REF_DATABASE_ROOD: DatabaseReference
lateinit var USER: User
lateinit var UID: String

const val NODE_USERS = "users"
const val NODE_USERNAMES = "username"

const val CHILD_BIO = "bio"
const val CHILD_ID = "id"
const val CHILD_PHONE = "phone"
const val CHILD_USERNAME = "username"
const val CHILD_FULLNAME = "fullname"


fun initFirebase() {
    AUTH = FirebaseAuth.getInstance()
    REF_DATABASE_ROOD = FirebaseDatabase.getInstance().reference
    USER = User()
    UID = AUTH.currentUser?.uid.toString()
}