package com.example.katherine.comicbykotlin

/**
 * Created by katherine on 6/12/17.
 */
data class USE_URL(
        val HOME_URL: String = "http://app.u17.com/v3/appV3/android/phone/comic/boutiqueList?android_id=0A00270000130000&v=3070099&model=VPhone&come_from=wandoujia",
        val RANK_TITLE_URL: String = "http://app.u17.com/v3/appV3/android/phone/rank/list?android_id=0A00270000130000&v=3070099&model=VPhone&come_from=wandoujia",
        val RANK_CONTENT_URL: String = "http://app.u17.com/v3/appV3/android/phone/list/commonComicList?argName=sort&android_id=0A00270000130000&v=3070099&model=VPhone&come_from=wandoujia&argValue=",
        val SORT_TITLE_URL:String = "http://app.u17.com/v3/appV3/android/phone/sort/mobileCateList?v=3120100&key=bc51c447bda6fca8ed6c1fca7f707c96aa9461ecd83ebe8da8dbe7e1cc0973e2889e11972ffb439739ea78e65a2cc6ebd4ceefee0ec3e902f228a807f6be94e831632e56961f7e95512c0e4bc0c17748105837a8ccb8eb173c5e60dc400f7bdf%253A%253A%253Aopen&model=KIW-TL00H&come_from=HUAWEI&android_id=d89014eb2314698b&version=2",
        val SORT_CONTENT_URL:String = "http://app.u17.com/v3/appV3/android/phone/list/commonComicList?argCon=4&page=1&v=3120100&model=KIW-TL00H&come_from=HUAWEI&android_id=d89014eb2314698b",
        val HOTWORD_URL: String = "http://app.u17.com/v3/appV3/android/phone/search/hotkeywords?android_id=3000048383665174&v=3070099&model=GT-P5210&t=1466134822&come_from=wandoujia",
        val KEYWORD_URL: String = "http://app.u17.com/v3/appV3/android/phone/search/relative?android_id=3000048383665174&v=3070099&model=GT-P5210&inputText=",
        val BOOK_URL: String = "http://app.u17.com/v3/appV3/android/phone/comic/detail_static?android_id=0A00270000130000&v=3070099&model=VPhone&come_from=wandoujia&comicid=",
        val CHAPTER_URL: String = "http://app.u17.com/v3/appV3/android/phone/comic/chapterNew?v=3120100&chapter_id=",
        val CONTENT_URL: String = "http://app.u17.com/v3/appV3/android/phone/comic/chapterlist?android_id=3000048383665174&v=3070099&model=GT-P5210&come_from=wandoujia&comicid="
)