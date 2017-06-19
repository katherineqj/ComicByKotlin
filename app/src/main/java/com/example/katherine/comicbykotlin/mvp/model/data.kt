package com.example.katherine.comicbykotlin.mvp.model

/**
 * Created by katherine on 6/12/17.
 */
data class Book(val title: String, val category: String, val type: Int, val cover: String, val link: String)

data class Chapter(val title: String, val link: String)

data class Page(val title: String, val link: String)