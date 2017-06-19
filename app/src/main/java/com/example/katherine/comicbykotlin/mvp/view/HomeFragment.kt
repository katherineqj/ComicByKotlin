package com.example.katherine.comicbykotlin.mvp.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.katherine.comicbykotlin.USE_URL
import com.example.katherine.comicbykotlin.mvp.model.getJsonString
import com.example.katherine.comicbykotlin.mvp.presenter.BookPresenter
import org.jetbrains.anko.async


/**
 * Created by katherine on 6/12/17.
 */
class HomeFragment(): Fragment(){
//Kotlin的class并不支持static变量,所以需要使用companion object来声明static变量
companion object {
    val AIM_URL :String = USE_URL().HOME_URL

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        async {  BookPresenter().obtain(AIM_URL) }
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}