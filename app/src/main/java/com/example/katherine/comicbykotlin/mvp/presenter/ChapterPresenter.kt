package com.example.katherine.comicbykotlin.mvp.presenter

import com.example.katherine.comicbykotlin.USE_URL
import com.example.katherine.comicbykotlin.mvp.model.Chapter
import com.example.katherine.comicbykotlin.mvp.model.getJsonString
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by katherine on 6/21/17.
 */
class ChapterPresenter:Presenter<ArrayList<Chapter>> {
    override fun obtain(url: String): ArrayList<Chapter> {
        val list = ArrayList<Chapter>()
        val JsonString = getJsonString(url)
        val all :JSONObject= JSONObject(JsonString)
        val code:Int = all.optInt("code")
        if (code==1){
            val data :JSONObject = all.optJSONObject("data")
            val stateCode :Int = data.optInt("stateCode")
            val  message : String  = data.optString("message")
            if (stateCode==1 && message.equals("成功")){
                val returnDate:JSONObject = data.optJSONObject("returnData")
                val chapter_list:JSONArray = returnDate.optJSONArray("chapter_list")
                var i :Int = 0
                while (i<chapter_list.length()){
                    val chapter:JSONObject = chapter_list.getJSONObject(i)
                    val title:String = chapter.optString("name")
                    val link :String = USE_URL().CHAPTER_URL+chapter.optInt("chapter_id")
                    val bean:Chapter = Chapter(title,link)
                    list.add(bean)
                    i++
                }
            }

        }


return list
    }
}
