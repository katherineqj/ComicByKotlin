package com.example.katherine.comicbykotlin.mvp.presenter

import com.example.katherine.comicbykotlin.mvp.model.Page
import com.example.katherine.comicbykotlin.mvp.model.getJsonString
import org.json.JSONObject
import java.util.ArrayList

/**
 * Created by katherine on 6/23/17.
 */
class PagePresenter() : Presenter<ArrayList<Page>> {

    override fun obtain(url: String): ArrayList<Page> {
        val list = ArrayList<Page>()
        val JsonString  = getJsonString(url)
        val all = JSONObject(JsonString)
        val code = all.getInt("code")
        if (code == 1) {
            val data = all.getJSONObject("data")
            val stateCode = data.getInt("stateCode")
            val message = data.getString("message")
            if (stateCode == 1 && message.contains("成功")) {
                val returnData = data.getJSONObject("returnData")
                val imageList = returnData.getJSONArray("image_list")
                var i: Int = 0
                while (i < imageList.length()) {
                    val image = imageList.get(i) as JSONObject
                    val title = image.getString("image_id")
                    val link = image.getString("location")
                    val bean = Page(title, link)
                    list.add(bean)
                    i++
                }

            }
        }
        return list;
    }

}