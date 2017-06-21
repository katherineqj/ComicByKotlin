package com.example.katherine.comicbykotlin.mvp.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.katherine.comicbykotlin.R


/**
 * Created by katherine on 6/12/17.
 */
class RankFragment(): Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.test,container,false)
    }
}