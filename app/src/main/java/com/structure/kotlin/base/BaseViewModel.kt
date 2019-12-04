package com.structure.kotlin.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import android.content.Context
import androidx.annotation.StringRes
import com.structure.kotlin.injection.component.DaggerVMInjector
import com.structure.kotlin.injection.VMInjector
import com.structure.kotlin.injection.ContextModule
import com.structure.kotlin.injection.NetworkModule
import com.structure.kotlin.ui.LoginViewModel

import com.structure.kotlin.utills.Utility

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    val isLoading = MutableLiveData<Boolean>()
    val errMsg = MutableLiveData<String>()
    val successMsg = MutableLiveData<String>()

    private val injector: VMInjector = DaggerVMInjector
        .builder()
        .contextModule(ContextModule)
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }


    override fun onCleared() {
        super.onCleared()
    }


    protected fun hasInternet(): Boolean {
        return Utility.hasInternet(getApplication<Application>().baseContext)
    }

    fun setIsLoading(isLoading: Boolean?) {
        this.isLoading.postValue(isLoading)
    }

    fun setErrMsg(@StringRes errMsg: Int) {
        this.errMsg.postValue(getAppContext().getString(errMsg))
    }


    fun setSuccessMsg(successMsg: String) {
        this.successMsg.value = successMsg
    }

    fun getAppContext(): Context {
        return getApplication<Application>().applicationContext
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is LoginViewModel -> injector.inject(this)
        }
    }

}