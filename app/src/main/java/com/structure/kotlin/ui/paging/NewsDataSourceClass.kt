package com.structure.kotlin.ui.paging

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.imagepicker.pdfpicker.Constant
import com.structure.kotlin.network.RestApi
import com.structure.kotlin.utills.Constants
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by ${Saquib} on 12-08-2018.
 */
class NewsDataSourceClass(var restApi: RestApi) : PageKeyedDataSource<Int, NewsModelClass>() {

    private var sourceIndex: Int = 0


    companion object {
        var progressLiveStatus = MutableLiveData<Boolean>()
        var errorMsgLiveString = MutableLiveData<String>()
    }


    @SuppressLint("CheckResult")
    override fun loadInitial(
        params: PageKeyedDataSource.LoadInitialParams<Int>,
        callback: PageKeyedDataSource.LoadInitialCallback<Int, NewsModelClass>
    ) {
//        restApi.getUser(sourceIndex, 20)
//            .doOnSubscribe {
//                progressLiveStatus.postValue(true)
//            }
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
//            .doOnTerminate {
//                progressLiveStatus.postValue(true)
//            }.subscribe(
//                { userList ->
//                    userList.let {
//                        sourceIndex++
//                        callback.onResult(userList, 0, 150, null, sourceIndex)
//                    }
//                },
//                {
//                    errorMsgLiveString.value = it.message
//
//                }
//            )

        restApi.fetchListNews(Constants.sources[sourceIndex], Constants.API_KEY)
            .doOnSubscribe {
                progressLiveStatus.postValue(true)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnTerminate {
                progressLiveStatus.postValue(true)
            }.subscribe(
                { userList ->
                    userList.let {
                        sourceIndex++
                        callback.onResult(userList.articles, 0, 40, null, sourceIndex)
                    }
                },
                {
                    errorMsgLiveString.value = it.message

                }
            )

    }

    override fun loadBefore(
        params: PageKeyedDataSource.LoadParams<Int>,
        callback: PageKeyedDataSource.LoadCallback<Int, NewsModelClass>
    ) {

    }

    @SuppressLint("CheckResult")
    override fun loadAfter(
        params: PageKeyedDataSource.LoadParams<Int>,
        callback: PageKeyedDataSource.LoadCallback<Int, NewsModelClass>
    ) {
//        restApi.getUser(params.key, 20)
//            .doOnSubscribe {
//                progressLiveStatus.postValue(true)
//            }
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
//            .doOnTerminate {
//                progressLiveStatus.postValue(false)
//            }.subscribe(
//                { userList ->
//                    userList.let {
//                        sourceIndex++
//                        callback.onResult(userList, params.key + 1)
//                    }
//                },
//                {
//                    errorMsgLiveString.value = it.message
//
//                }
//            )
        restApi.fetchListNews(Constants.sources.get(params.key-1), Constants.API_KEY)
            .doOnSubscribe {
                progressLiveStatus.postValue(true)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnTerminate {
                progressLiveStatus.postValue(true)
            }.subscribe(
                { userList ->
                    userList.let {
                        sourceIndex++
                        val v = if (params.key==3) null else params.key+1
                        callback.onResult(userList.articles,v)
                    }
                },
                {
                    errorMsgLiveString.value = it.message

                }
            )

    }

}
