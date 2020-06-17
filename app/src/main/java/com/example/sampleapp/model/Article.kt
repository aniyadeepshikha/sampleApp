package com.example.sampleapp.model

data class Article(val id : String, val createdAt : String, val content :String, val comments : Double, val likes : Double, val  media : List<Media>,
val user : List<User>)
