package com.example.katherine.comicbykotlin.mvp.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import com.example.katherine.comicbykotlin.R
import com.example.katherine.comicbykotlin.mvp.model.Page
import com.example.katherine.comicbykotlin.mvp.presenter.PagePresenter
import kotlinx.android.synthetic.main.activity_pager.*
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread

/**
 * Created by katherine on 6/22/17.
 */
class PagerActivity : AppCompatActivity(){
    companion object {
        val INTENT_LINK = "link"
    }
    lateinit var link:String
    var mDate = ArrayList<Page>()
    lateinit var adapter:PageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pager)
        init()
    }
    override fun onResume() {
        super.onResume()
        async() {
           val data = PagePresenter().obtain(link)
            mDate = data
            uiThread {
                adapter.refreshData(data)
            }
        }
    }
    private fun init(){
        link = intent.getStringExtra(INTENT_LINK)
        adapter = PageAdapter(mDate, supportFragmentManager)
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 2
    }
    class PageAdapter(var data: java.util.ArrayList<Page> = java.util.ArrayList<Page>(), fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

        override fun getCount(): Int = data.size

        override fun getItem(position: Int): Fragment? = newInstance(data[position].link)

        fun refreshData(newData: java.util.ArrayList<Page>) {
            data = newData
            notifyDataSetChanged()
        }

        fun newInstance(link: String) = PageFragment(link)
    }
}