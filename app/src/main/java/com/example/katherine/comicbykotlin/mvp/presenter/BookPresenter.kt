package com.example.katherine.comicbykotlin.mvp.presenter

import com.example.katherine.comicbykotlin.USE_URL
import com.example.katherine.comicbykotlin.mvp.model.Book
import com.example.katherine.comicbykotlin.mvp.model.getJsonString
import com.example.katherine.comicbykotlin.mvp.view.HomeFragment
import org.json.JSONObject

/**
 * Created by katherine on 6/12/17.
 */
class BookPresenter():Presenter<ArrayList<Book>> {
    override fun obtain(url: String): ArrayList<Book> {
        val list = ArrayList<Book>()
        val JsonString = getJsonString(url)
        println(JsonString)
        val all = JSONObject(JsonString)
        val code = all.getInt("code")
        if (code == 1) {
            val data = all.getJSONObject("data")
            val stateCode = data.getInt("stateCode")
            val message = data.getString("message")
            if (stateCode == 1 && message.equals("成功")) {
                val returnData = data.getJSONObject("returnData")
                val comicsLists = returnData.getJSONArray("comicLists")
                var i = 0
                while (i < comicsLists.length()) {
                    val comicsList = comicsLists.get(i) as JSONObject
                    val comics = comicsList.getJSONArray("comics")
                    val itemTitle = comicsList.getString("itemTitle")
                    val itemIcon = comicsList.getString("titleIconUrl")
                    val bean = Book(itemTitle, "", 0, itemIcon, "")
                    list.add(bean)
                    var j: Int = 0
                    while (j < comics.length()) {
                        val comic = comics.get(j) as JSONObject
                        val title = comic.getString("name")
                        val category = comic.getString("short_description")
                        val cover = comic.getString("cover")
                        val link = USE_URL().BOOK_URL + comic.getInt("comicId")
                        val bean = Book(title, category, 1, cover, link)
                        list.add(bean)
                        j++
                    }
                    i++


                }

            }
        }
        return list
    }

    fun sortTitle(url: String): ArrayList<Book> {
        val sort_list = ArrayList<Book>()
        val jsonString = getJsonString(url)
        val all: JSONObject = JSONObject(jsonString)
        val code: Int = all.optInt("code")
        if (code == 1) {
            val data: JSONObject = all.optJSONObject("data")
            val stateCode: Int = data.optInt("stateCode")
            val message: String = data.optString("message")
            if (stateCode == 1 && message.equals("成功")) {
                val returnData = data.optJSONObject("returnData")
                val rankingList = returnData.optJSONArray("rankingList")
                var i = 0
                while (i < rankingList.length()) {
                    val rank = rankingList.get(i) as JSONObject
                    val sortName = rank.optString("sortName")
                    val cover = rank.optString("cover")
                    val link = USE_URL().SORT_CONTENT_URL + "&argValue=" + rank.getInt("argValue") + "&argName=" + rank.getString("argName")
                    val bean = Book(sortName, "", 1, cover, link)
                    sort_list.add(bean)
                    i++

                }
            }
        }

        return sort_list

    }
    fun sortContent(url: String): java.util.ArrayList<Book> {
        val list = java.util.ArrayList<Book>()
        val html = getJsonString(url)
        val all = JSONObject(html)
        val code = all.getInt("code")
        if (code == 1) {
            val data = all.getJSONObject("data")
            val stateCode = data.getInt("stateCode")
            val message = data.getString("message")
            if (stateCode == 1 && message.equals("成功")) {
                val returnData = data.getJSONObject("returnData")
                val comics = returnData.getJSONArray("comics")
                var i: Int = 0
                while (i < comics.length()) {
                    val comic = comics.get(i) as JSONObject
                    val title = comic.getString("name")
                    val category = comic.getString("description")
                    val cover = comic.getString("cover")
                    val link = USE_URL().BOOK_URL + comic.getInt("comicId")
                    val bean = Book(title, category, 1, cover, link)
                    list.add(bean)
                    i++
                }
            }
        }
        return list
    }
}