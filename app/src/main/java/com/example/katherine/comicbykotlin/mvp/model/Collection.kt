package com.example.katherine.comicbykotlin.mvp.model

import android.app.DownloadManager
import android.support.design.widget.Snackbar
import android.view.View
import com.squareup.okhttp.Request

/**
 * Created by katherine on 6/12/17.
 */
fun getJsonString(url:String):String{
    val okclient = OkClient.instance()
    val request = Request.Builder().url(url).build()
    val response = okclient.newCall(request).execute()
    return response.body().string()

}
fun View.snackbar(messageRes: Int, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this,messageRes,duration).show()
}