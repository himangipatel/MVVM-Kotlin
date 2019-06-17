package com.structure.kotlin.ui.paging

import android.app.Application
import androidx.arch.core.util.Function
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.structure.kotlin.base.BaseViewModel
import com.structure.kotlin.network.RestApi
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


/**
 * Created by ${Saquib} on 12-08-2018.
 */
class PagingLibViewModel(application: Application) : BaseViewModel(application) {

    @Inject
    lateinit var restApi: RestApi

    private val newsDataSourceFactory: NewsDataSourceFactory
    private lateinit var listLiveData: LiveData<PagedList<NewsModelClass>>

    var progressLoadStatus: LiveData<String> = MutableLiveData()


    init {
        newsDataSourceFactory = NewsDataSourceFactory(restApi)
        initializePaging()
    }


    private fun initializePaging() {

        val pagedListConfig = PagedList.Config.Builder()
            .setInitialLoadSizeHint(10)
            .setPrefetchDistance(3)
            .setEnablePlaceholders(true)
            .setPageSize(20)
            .build()

        listLiveData = LivePagedListBuilder(newsDataSourceFactory, pagedListConfig)
            .build()

       // Transformations.switchMap(newsDataSourceFactory.mutableLiveData,NewsDataSourceClass.errorMsgLiveString)

    }

//    fun getProgressLoadStatus(): LiveData<String> {
//        return progressLoadStatus
//    }

    fun getListLiveData(): LiveData<PagedList<NewsModelClass>> {
        return listLiveData
    }



}