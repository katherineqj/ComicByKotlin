package com.example.katherine.comicbykotlin.mvp.presenter

/**
 * Created by katherine on 6/12/17.
 */
interface Presenter<T> {
    fun obtain(url: String):T
}