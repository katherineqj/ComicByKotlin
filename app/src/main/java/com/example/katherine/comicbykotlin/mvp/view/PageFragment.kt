package com.example.katherine.comicbykotlin.mvp.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import com.example.katherine.comicbykotlin.R
import com.example.katherine.comicbykotlin.mvp.model.snackbar
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

/**
 * Created by katherine on 6/22/17.
 */
class PageFragment(var link:String): Fragment() {
    lateinit var progressBar: ProgressBar
    lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_page,container,false)
        progressBar = view.find(R.id.progressBar)
        imageView = view.find(R.id.imageView)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar.visibility = View.VISIBLE

    }

    override fun onResume() {
        super.onResume()
        Picasso.with(context).load(link).placeholder(R.color.white).into(imageView,object : Callback {
            override fun onSuccess() {
                 progressBar.visibility = View.GONE            }

            override fun onError() {
                imageView.snackbar(R.string.network_error)
            }
        })
    }
}