package com.structure.kotlin.ui

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.structure.kotlin.R
import com.structure.kotlin.utills.ApiUtility
import com.structure.kotlin.base.BaseViewModel
import com.structure.kotlin.model.LoginUserData
import com.structure.kotlin.network.RestApi
import com.structure.kotlin.validator.ValidationErrorModel
import com.structure.kotlin.validator.Validator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Himangi on 4/1/19
 */
class LoginViewModel(application: Application) : BaseViewModel(application) {

    var validateErrModel = MutableLiveData<ValidationErrorModel>()

    var userData = MutableLiveData<LoginUserData>()

    @Inject
    lateinit var restApi: RestApi

    private var subscription: Disposable? = null

    private fun authenticateUser(loginUserData: LoginUserData) {
        if (hasInternet()) {
            setIsLoading(true)
            subscription = restApi
                .authenticateUser(ApiUtility.LOGIN, loginUserData)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnTerminate { setIsLoading(false) }
                .subscribe(
                    {
                        setSuccessMsg(it.token)
                        setIsLoading(true)
                        userData.value = it
                    },
                    {
                        setIsLoading(false)
                        setErrMsg(R.string.unknown_error)

                    }
                )
        } else {
            setErrMsg(R.string.no_internet)
        }
    }

    fun isValidEmailPassWord(loginUserData: LoginUserData) {

        Validator.validateEmail(loginUserData.userEmail)?.let {
            validateErrModel.value = it
            return
        }

        authenticateUser(loginUserData)

    }

    override fun onCleared() {
        super.onCleared()
    }


}