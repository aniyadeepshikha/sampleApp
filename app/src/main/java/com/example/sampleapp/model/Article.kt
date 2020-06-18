package com.example.sampleapp.model

data class Article(val id : String= "", val createdAt : String="", val content :String="", val comments : Double = 0.0, val likes : Double = 0.0, val  media : List<Media> = mutableListOf<Media>(),
                   val user : List<User> = mutableListOf())
