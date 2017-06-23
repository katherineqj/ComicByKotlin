package com.example.katherine.comicbykotlin.mvp.view

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.katherine.comicbykotlin.R
import com.example.katherine.comicbykotlin.USE_URL
import com.example.katherine.comicbykotlin.adapter.BookAdapter
import com.example.katherine.comicbykotlin.mvp.model.Book
import com.example.katherine.comicbykotlin.mvp.presenter.BookPresenter
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread

/**
 * Created by katherine on 6/12/17.
 */
class SortFragment () :Fragment() {
    companion object {
        val AIM_URL: String = USE_URL().SORT_TITLE_URL
    }

    var mDate: ArrayList<Book> = ArrayList<Book>()
    lateinit var adapter: BookAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var swipeRefresh: SwipeRefreshLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
          return inflater.inflate(R.layout.fragment_book,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)

    }
/*  onViewCreated在onCreateView执行完后立即执行。
    onCreateView返回的就是fragment要显示的view
    将findViewbyid和一些初始化数据的内容放置到onViewCreated中，
    其是在加载完布局后调用。因为只有在onCreateView后界面才能加载出来了，
    所以将findViewbyid和初始化一些数据放置其中可以避免出现如切换界面时
    出现卡顿的现象，提高用户体验*/
    private fun initView(view:View){
    swipeRefresh = view.findViewById(R.id.swipeRefresh) as SwipeRefreshLayout
    swipeRefresh.setColorSchemeResources(R.color.blue, R.color.teal, R.color.green, R.color.yellow, R.color.orange, R.color.red, R.color.pink, R.color.purple);
    recyclerView = view.findViewById(R.id.recyclerView) as RecyclerView
    recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    adapter = BookAdapter { _: View, i: Int -> jump2Sort(i) }
    recyclerView.adapter = adapter
    swipeRefresh.setOnRefreshListener {
        load()
    }
    swipeRefresh.post { swipeRefresh.isRefreshing = true }

    }
    private fun jump2Sort(position: Int) {
        var intent = Intent(context, SortActivity().javaClass)
        intent.putExtra(ChapterActivity.INTENT_LINK, mDate[position].link)
        intent.putExtra(ChapterActivity.INTENT_COVER, mDate[position].cover)
        intent.putExtra(ChapterActivity.INTENT_TITLE, mDate[position].title)
        startActivity(intent)
    }
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && mDate.size == 0) {
            load()
        }
    }
   /* 当fragment被用户可见时，setUserVisibleHint()会调用且传入true值，
    当fragment不被用户可见时，setUserVisibleHint()则得到false值。
    而在传统的fragment生命周期里也看不到这个函数。
    viewpager监听切换tab事件，tab切换一次，执行一次setUserVisibleHint()方法
    setUserVisibleHint() 在上图所示fragment所有生命周期之前，
    无论viewpager是在activity哪个生命周期里初始化。
    activity生命周期 和 fragment生命周期 时序并不是按序来的，
    也就是说fragment的oncreate方法时序并不一定在activity的oncreate方法之后。*/
    private fun load() {
        async() {
            val data = BookPresenter().sortTitle(AIM_URL)
            uiThread {
                mDate = data
                adapter.refreshData(data)
                swipeRefresh.isRefreshing = false
            }
        }
    }
}
