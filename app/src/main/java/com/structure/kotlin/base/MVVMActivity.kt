package com.structure.kotlin.base

import androidx.lifecycle.Observer
import android.os.Bundle
import com.structure.kotlin.utills.Utility


abstract class MVVMActivity<A : BaseViewModel> : BaseActivity() {

    protected lateinit var viewModel: A

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = createViewModel()
        viewModel.isLoading.observe(this, loadingStatusObserver)
        viewModel.errMsg.observe(this, errMsgObserver)
        viewModel.successMsg.observe(this, successMsgObserver)
    }

    /**
     * Instantiates the viewModel the Activity is based on.
     */
    protected abstract fun createViewModel(): A

    private var loadingStatusObserver = Observer<Boolean> { isLoading ->
        if (isLoading == null) return@Observer
        showLoading(isLoading)
    }

    private val errMsgObserver = Observer<String> { s ->
        Utility.showSnackBar(findViewById(android.R.id.content), s!!)
    }

    private val successMsgObserver =
        Observer<String> { s -> Utility.showSnackBar(findViewById(android.R.id.content), s!!) }

}
