package com.example.katherine.comicbykotlin.mvp.view

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.view.View
import com.example.katherine.comicbykotlin.R
import com.example.katherine.comicbykotlin.adapter.BookAdapter
import com.example.katherine.comicbykotlin.mvp.model.Book
import com.example.katherine.comicbykotlin.mvp.presenter.BookPresenter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_chapter.*
import org.jetbrains.anko.async
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread
import java.util.ArrayList

/**
 * Created by katherine on 6/23/17.
 */
class SortActivity:AppCompatActivity() {
    companion object {


        val INTENT_LINK = "link"
        val INTENT_COVER = "cover"
        val INTENT_TITLE = "title"
    }

    lateinit var link: String
    lateinit var cover: String
    lateinit var title: String
    var mData = ArrayList<Book>()
    lateinit var adapter: BookAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var swipeRefresh: SwipeRefreshLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter)
        initView()
    }
    private fun initView(){
        link = intent.getStringExtra(INTENT_LINK)
        cover = intent.getStringExtra(INTENT_COVER)
        title = intent.getStringExtra(INTENT_TITLE)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        collapsingToolbar.title = title
        if (!cover.equals("")) {
            Picasso.with(this).load(cover).into(imageView)
        }
        swipeRefresh = find(R.id.swipeRefresh)
        swipeRefresh.setColorSchemeResources(R.color.blue, R.color.teal, R.color.green, R.color.yellow, R.color.orange, R.color.red, R.color.pink, R.color.purple)
        swipeRefresh.setOnRefreshListener { load() }
        recyclerView = find(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2) as RecyclerView.LayoutManager?
        adapter = BookAdapter { _: View, i: Int -> jump2Chapter(i) }
        recyclerView.adapter = adapter
    }
    private fun jump2Chapter(position: Int) {
        var intent = Intent(this, ChapterActivity().javaClass)
        intent.putExtra(ChapterActivity.INTENT_LINK, mData[position].link)
        intent.putExtra(ChapterActivity.INTENT_COVER, mData[position].cover)
        intent.putExtra(ChapterActivity.INTENT_TITLE, mData[position].title)
        intent.putExtra(ChapterActivity.INTENT_CATEGORY, mData[position].category)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        swipeRefresh.post { swipeRefresh.isRefreshing = true }
        load()
    }

    private fun load() {
        async() {
            val data = BookPresenter().sortContent(link)
            uiThread {
                mData = data
                adapter.refreshData(data)
                swipeRefresh.isRefreshing = false
            }
        }
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