package com.example.katherine.comicbykotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import com.example.katherine.comicbykotlin.adapter.ViewPagerAdapter

import com.example.katherine.comicbykotlin.mvp.view.HomeFragment
import com.example.katherine.comicbykotlin.mvp.view.SortFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import me.lancer.comicface_kotlin.mvp.book.fragment.RankFragment

class MainActivity : AppCompatActivity() {
    val strings:ArrayList<Int> = arrayListOf(R.string.tab_one,R.string.tab_two,R.string.tab_three)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    private fun init(){
        setSupportActionBar(toolbar)
        val fragments = java.util.ArrayList<Fragment>()
        fragments.add(HomeFragment())
        fragments.add(RankFragment())
        fragments.add(SortFragment())
        val titles:List<String> = strings.map(this::getString)
        viewpager.adapter = ViewPagerAdapter(fragments,titles,supportFragmentManager)
        viewpager.offscreenPageLimit = 2
        tab.setupWithViewPager(viewpager)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //当客户点击MENU按钮的时候，调用该方法 会出现menu菜单
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
   }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //当客户点击菜单当中的某一个选项时，会调用该方法
        val id = item.itemId
        if (id == R.id.menu_my){
           /*val intent = Intent(this,)
            startActivity(intent)
            return true*/
        }
        if (id == R.id.menu_room){
            /*val intent = Intent(this,)
             startActivity(intent)
             return true*/
        }
        return super.onOptionsItemSelected(item)
    }
}
