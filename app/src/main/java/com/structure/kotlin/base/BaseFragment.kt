package com.structure.kotlin.base


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.structure.kotlin.utills.Layout
import com.structure.kotlin.utills.Utility


abstract class BaseFragment : Fragment(), View.OnClickListener {

    val layout: Layout? get() = javaClass.getAnnotation(Layout::class.java)
    lateinit var inflater: LayoutInflater
    lateinit var container: ViewGroup

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.inflater = inflater
        this.container = container!!
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    protected fun <T : ViewDataBinding> getBindingClass(): T {
        return DataBindingUtil.inflate(
            inflater, layout!!.value, container, false
        )
    }

    fun hasInternet(): Boolean {
        return Utility.hasInternet(this.activity!!)
    }


    protected fun clickableViews(views: Array<View>) {
        for (view in views) {
            view.setOnClickListener(this)
        }
    }

    /**
     * A method to show progress dialog on center of screen during api calls
     */
    open fun showLoading() {
        (activity as BaseActivity).showLoading()
    }

    fun showLoading(isLoading: Boolean) {
        if (isLoading) showLoading()
        else hideLoading()
    }


    /**
     * A method to hide the progress dialog
     */
    open fun hideLoading() {
        (activity as BaseActivity).hideLoading()
    }


    /**
     * A base method to show internet error for all activities
     */
    open fun onInternetFailure(message: Int) {
        (activity as BaseActivity).onInternetFailure(message)
    }

    /**
     * A base method to log out user when an authentication failure occurs
     */
    fun onAuthenticationFailure() {
        activity?.let { Utility.logoutUser(it) }
    }
}