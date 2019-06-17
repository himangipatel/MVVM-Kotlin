package com.structure.kotlin.base

import android.os.Bundle


/**
 * Created by Rujul Gandhi on 4/1/18.
 */

abstract class MVVMFragment<P : BaseViewModel> : BaseFragment(){

    protected lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = instantiatePresenter()
    }

    /**
     * Instantiates the viewModel the Activity is based on.
     */
    protected abstract fun instantiatePresenter(): P

}
