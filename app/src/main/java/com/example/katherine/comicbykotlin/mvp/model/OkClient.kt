package com.example.katherine.comicbykotlin.mvp.model

import com.squareup.okhttp.OkHttpClient

/**
 * Created by katherine on 6/12/17.
 */
object OkClient{
    private  val  client = OkHttpClient()
    fun instance() = client

}