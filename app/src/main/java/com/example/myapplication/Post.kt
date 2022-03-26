package com.example.myapplication

data class Post(
    val id:Int,
    val author:String,
    val Category: String,
    val parent:Int ,
    val title: String,
    val date:String,
    val content:String,
    val upLong:Long,
    val downVote:Long,
    val comment: Array<String>
)
