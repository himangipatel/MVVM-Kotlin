package com.structure.kotlin.ui.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.structure.kotlin.network.RestApi

import io.reactivex.disposables.CompositeDisposable


/**
 * Created by ${Saquib} on 12-08-2018.
 */
class NewsDataSourceFactory(var restApi: RestApi) : DataSource.Factory<Int, NewsModelClass>() {

    val mutableLiveData: MutableLiveData<NewsDataSourceClass> = MutableLiveData()

    override fun create(): DataSource<Int, NewsModelClass> {
        val dataSourceClass = NewsDataSourceClass(restApi)
        mutableLiveData.postValue(dataSourceClass)
        return dataSourceClass
    }
}
