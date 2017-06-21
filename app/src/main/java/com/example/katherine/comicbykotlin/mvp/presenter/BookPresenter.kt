package com.example.katherine.comicbykotlin.mvp.presenter

import com.example.katherine.comicbykotlin.USE_URL
import com.example.katherine.comicbykotlin.mvp.model.Book
import com.example.katherine.comicbykotlin.mvp.model.getJsonString
import com.example.katherine.comicbykotlin.mvp.view.HomeFragment
import org.json.JSONObject

/**
 * Created by katherine on 6/12/17.
 */
class BookPresenter():Presenter<ArrayList<Book>>{
    override fun obtain(url: String): ArrayList<Book> {
        val list = ArrayList<Book>()
        val JsonString = getJsonString(url)
        println(JsonString)
        val all  = JSONObject(JsonString)
        val code = all.getInt("code")
        if (code==1){
            val data = all.getJSONObject("data")
            val stateCode = data.getInt("stateCode")
            val message = data.getString("message")
            if (stateCode == 1 && message.equals("成功")){
                val returnData = data.getJSONObject("returnData")
                val comicsLists = returnData.getJSONArray("comicLists")
                var i = 0
                while (i <comicsLists.length()){
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

}