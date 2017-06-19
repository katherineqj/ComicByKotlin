package com.example.katherine.comicbykotlin.mvp.presenter

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
        val JsonString = getJsonString(HomeFragment.AIM_URL)
        println(JsonString)
        val all  = JSONObject(JsonString)
        val code = all.getInt("code")
        if (code==1){
            val data = all.getJSONObject("data")
            val stateCode = data.getInt("stateCode")
            val message = data.getString("成功")
            if (stateCode == 1 && message.equals("成功")){
                val returnData = data.getJSONObject("returnData")
                val comicLists = returnData.getJSONArray("comicLists")
                var i = 0
                while (i <comicLists.length()){

                }

            }
        }
        return list
    }

}