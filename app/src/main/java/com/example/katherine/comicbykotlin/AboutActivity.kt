package com.example.katherine.comicbykotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.webkit.*
import kotlinx.android.synthetic.main.activity_about.*
import org.jetbrains.anko.image

/**
 * Created by katherine on 6/24/17.
 */
class AboutActivity :AppCompatActivity(){
    companion object {
        val TYPE_MY: String  ="my"
        val TYPE_ROOM: String = "room"
        val TYPE: String = "Type"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        var type = intent.getStringExtra(TYPE)
        init()
        if (type.equals(TYPE_MY)){
            collapsingToolbar.title = getString(R.string.about_one)
            imageView.image = resources.getDrawable(R.mipmap.my)
            webView.loadUrl("https://github.com/katherineqj")

        }else if(type.equals(TYPE_ROOM)){
            collapsingToolbar.title = getString(R.string.about_two)
            imageView.image = resources.getDrawable(R.mipmap.room)
            webView.loadUrl("https://xiyoumobile.com/")
        }

    }
    private fun init(){
        setSupportActionBar(toolbar_about)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        webView.getSettings().setSupportZoom(true)
        webView.getSettings().setBuiltInZoomControls(true)
        webView.getSettings().setUseWideViewPort(true)
        webView.getSettings().setJavaScriptEnabled(true)
        webView.getSettings().setLoadWithOverviewMode(true)
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN)
        webView.getSettings().setLoadWithOverviewMode(true)
        webView.setWebViewClient(object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                return false
            }

        })
        webView.setWebChromeClient( object :WebChromeClient(){
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                if (newProgress==100){
                    progressBar1.visibility = View.GONE
                }else{
                    progressBar1.visibility = View.VISIBLE
                    progressBar1.progress = newProgress
                }
            }
        })



    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == android.R.id.home) {
            onBackPressed()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

}