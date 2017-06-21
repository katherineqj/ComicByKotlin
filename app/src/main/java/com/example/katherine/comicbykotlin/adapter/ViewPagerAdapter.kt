package com.example.katherine.comicbykotlin.adapter

import android.support.v4.app.FragmentManager
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup

/**
 * Created by katherine on 6/11/17.
 */
class ViewPagerAdapter(
        val fragments: List<Fragment>,
        val nameList:List<String>,
        val fm:FragmentManager) : FragmentPagerAdapter(fm){
    override fun getItem(position: Int):Fragment  = fragments[position]
    override fun getCount(): Int =fragments.size
    override fun getPageTitle(position: Int): CharSequence =nameList[position]
    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        super.destroyItem(container, position, `object`)
    }

}