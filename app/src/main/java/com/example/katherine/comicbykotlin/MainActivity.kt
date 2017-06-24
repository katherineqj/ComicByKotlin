package com.example.katherine.comicbykotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import com.example.katherine.comicbykotlin.adapter.ViewPagerAdapter

import com.example.katherine.comicbykotlin.mvp.view.HomeFragment
import com.example.katherine.comicbykotlin.mvp.view.SortFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import me.lancer.comicface_kotlin.mvp.book.fragment.RankFragment
import java.lang.reflect.AccessibleObject.setAccessible
import java.lang.reflect.AccessibleObject.setAccessible





class MainActivity : AppCompatActivity() {
    val strings:ArrayList<Int> = arrayListOf(R.string.tab_one,R.string.tab_two,R.string.tab_three)
    companion object {
        val TYPE_MY: String  ="my"
        val TYPE_ROOM: String = "room"
        val TYPE: String = "Type"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    private fun init(){
        toolbar.setTitle(R.string.toorbar_title)
        toolbar.title = resources.getString(R.string.toorbar_title)
        toolbar.subtitle = resources.getString(R.string.toorbar_subtitle)
        setSupportActionBar(toolbar)
        toolbar.navigationIcon = resources.getDrawable(R.mipmap.cute1)
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
           val intent = Intent(this,AboutActivity().javaClass)
            intent.putExtra(TYPE,TYPE_MY)
            startActivity(intent)
            return true
        }
        if (id == R.id.menu_room){
            val intent = Intent(this,AboutActivity().javaClass)
            intent.putExtra(TYPE,TYPE_ROOM)
            startActivity(intent)
             return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onMenuOpened(featureId: Int, menu: Menu?): Boolean {
        if ( menu != null) {
            if (menu.javaClass.simpleName == "MenuBuilder") {
                try {
                    val m = menu.javaClass.getDeclaredMethod(
                            "setOptionalIconsVisible", java.lang.Boolean.TYPE)
                    m.isAccessible = true
                    m.invoke(menu, true)
                } catch (e: NoSuchMethodException) {

                } catch (e: Exception) {
                    throw RuntimeException(e)
                }

            }
        }
        return super.onMenuOpened(featureId, menu)
    }
}
