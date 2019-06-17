package com.structure.kotlin.ui.paging

import androidx.recyclerview.widget.DiffUtil
import com.squareup.moshi.Json


/**
 * Created by ${Saquib} on 12-08-2018.
 */
class NewsModelClass {

    @Json(name = "urlToImage")
    val newsImg: String = ""

    @Json(name = "title")
    val newsTitle: String? = null

    @Json(name = "html_url")
    var userProfile: String? = null



    companion object {

        @JvmField
        var DIFF_CALLBACK: DiffUtil.ItemCallback<NewsModelClass> = object : DiffUtil.ItemCallback<NewsModelClass>() {
            override fun areItemsTheSame(oldItem: NewsModelClass, newItem: NewsModelClass): Boolean {
                return oldItem.newsTitle == newItem.newsTitle
            }

            override fun areContentsTheSame(oldItem: NewsModelClass, newItem: NewsModelClass): Boolean {
                return oldItem == newItem
            }
        }
    }

}
