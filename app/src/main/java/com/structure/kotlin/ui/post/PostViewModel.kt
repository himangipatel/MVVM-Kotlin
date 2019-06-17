package com.structure.kotlin.ui.post

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.structure.kotlin.R
import com.structure.kotlin.apiutills.ApiUtility
import com.structure.kotlin.base.BaseViewModel
import com.structure.kotlin.model.Post
import com.structure.kotlin.network.RestApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostViewModel(application: Application) : BaseViewModel(application) {

    @Inject
    lateinit var restApi: RestApi

    private var subscription: Disposable? = null

    private var mutablePostList = MutableLiveData<List<Post>>()

    private fun loadPosts() {
        if (hasInternet()) {
            setIsLoading(true)
            subscription = restApi
                .getPosts(ApiUtility.GET_POSTS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnTerminate { setIsLoading(false) }
                .subscribe(
                    { posts ->
                        posts.let {
                            mutablePostList.value = it
                        }
                    },
                    {
                        setErrMsg(R.string.unknown_error)
                    }
                )
        } else {
            setErrMsg(R.string.unknown_error)
        }
    }


    fun getUserPost(): LiveData<List<Post>>? {
        if (mutablePostList.value == null || mutablePostList.value!!.isEmpty()
        ) {
            loadPosts()
        }
        return this.mutablePostList
    }


    override fun onCleared() {
        super.onCleared()
        subscription?.dispose()
    }

}