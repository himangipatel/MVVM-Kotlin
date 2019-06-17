package com.structure.kotlin.ui

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.structure.kotlin.R
import com.structure.kotlin.annotation.Layout
import com.structure.kotlin.base.MVVMActivity
import com.structure.kotlin.databinding.ActivityLoginBinding
import com.structure.kotlin.db.DatabaseHandler

import com.structure.kotlin.model.User

import com.structure.kotlin.validator.ValidationError
import com.structure.kotlin.validator.ValidationErrorModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*


/**
 * A login screen that offers login via email/password.
 */

@Layout(R.layout.activity_login)
class LoginActivity : MVVMActivity<LoginViewModel>() {

    override fun createViewModel(): LoginViewModel {
        return ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    lateinit var activityLoginBinding: ActivityLoginBinding
    private var db: DatabaseHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLoginBinding = getBindingClass()
        setTitleTextView("Login")
        showHideBack(View.VISIBLE)
        viewModel.validateErrModel.observe(this, validationErrorModelObserver)
        viewModel.userData.observe(this, Observer {

        })

    }

    private fun addDummyUsersData() {

        val userList = ArrayList<User>()
        userList.add(User("Himangi Patel", "xyz@gmail.com"))
        userList.add(User("User 1", "xyz1@gmail.com"))
        userList.add(User("User 2", "xyz2@gmail.com"))
        userList.add(User("User 3", "xyz3@gmail.com"))
        userList.add(User("User 4", "xyz4@gmail.com"))

        Observable.fromCallable({
            db = DatabaseHandler.getDatabase(context = this)
            db!!.clearAllTables()
            val userDao = db?.userDao()
            with(userDao) {
                this?.insert(userList)
            }
        }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()

    }

    override fun onClick(v: View) {
        super.onClick(v)
        when (v.id) {
            R.id.btnSignIn -> {

            }
            R.id.ivRight -> {

            }
        }
    }


    private val validationErrorModelObserver =
        Observer<ValidationErrorModel> { validationErrorModel ->
            if (validationErrorModel != null) {
                when (validationErrorModel.error) {
                    ValidationError.EMAIL -> {
                        email.requestFocus()
                        email.setError(getString(validationErrorModel.msg))
                    }
                    ValidationError.PASSWORD -> {
                        password.requestFocus()
                        password.setError(getString(validationErrorModel.msg))
                    }
                    else -> {
                    }
                }
            }
        }


}
