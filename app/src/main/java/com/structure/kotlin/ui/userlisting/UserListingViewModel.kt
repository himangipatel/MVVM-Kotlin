package com.structure.kotlin.ui.userlisting

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import android.content.Context

import com.structure.kotlin.network.RestApi
import com.structure.kotlin.base.BaseViewModel
import com.structure.kotlin.db.DatabaseHandler
import com.structure.kotlin.model.User
import io.reactivex.Observable

import java.util.concurrent.Callable
import javax.inject.Inject

/**
 * Created by Himangi on 11/1/19
 */
class UserListingViewModel(application: Application) : BaseViewModel(application) {


    private var userList: LiveData<List<User>>? = null
    private var userName: MutableLiveData<String>? = null

    fun getUserList(): LiveData<List<User>>? {
        if (userList == null) {
            getUsersFromDatabase()
        }
        return this.userList
    }


    private fun getUsersFromDatabase() {
        Observable.fromCallable(Callable<LiveData<List<User>>> {
            return@Callable DatabaseHandler.getDatabase(getAppContext()).userDao().allUsers
        }).doOnNext { users ->
            userList = users
        }.subscribe()
    }

    fun deleteUser(user: User) {
        DatabaseHandler.getDatabase(getAppContext()).userDao().delete(user)
    }

    fun insertUser(name: String, email: String) {
        DatabaseHandler
            .getDatabase(getAppContext())
            .userDao()
            .insert(
                User(name, email)
            )
    }

    fun updateUser(user: User) {
        DatabaseHandler
            .getDatabase(getAppContext())
            .userDao()
            .update(user)
    }


}