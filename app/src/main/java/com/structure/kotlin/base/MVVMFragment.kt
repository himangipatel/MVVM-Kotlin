package com.structure.kotlin.base

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.structure.kotlin.utills.Utility


/**
 * Created by Himangi Patel on 4/1/18.
 */

abstract class MVVMFragment<A : BaseViewModel> : BaseFragment() {

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
        if (!s.isNullOrBlank()) {
            Utility.showSnackBar(activity!!.findViewById(android.R.id.content), s)
        }
    }

    private val successMsgObserver =
        Observer<String> { s ->
            Utility.showSnackBar(
                activity!!.findViewById(android.R.id.content),
                s!!
            )
        }


}
