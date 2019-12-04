package com.structure.kotlin.base

import android.app.Dialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import com.structure.kotlin.R
import com.structure.kotlin.utills.Layout
import com.structure.kotlin.databinding.ActivityBaseBinding
import com.structure.kotlin.utills.Utility


abstract class BaseActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityBaseBinding
    private var progressDialog: Dialog? = null
    val layout: Layout? get() = javaClass.getAnnotation(Layout::class.java)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_base)
        clickableViews(arrayOf(binding.ivRight, binding.ivLeft, binding.tvTitle))
    }


    protected fun <T : ViewDataBinding> getBindingClass(): T {
        return DataBindingUtil.inflate(layoutInflater, layout!!.value, binding.layoutContainer, true)
    }


    open fun clickableViews(views: Array<View>) {
        for (view in views) {
            view.setOnClickListener(this)
        }
    }

    protected fun setToolBarVisibility(visibility: Int) {
        binding.baseView.setVisibility(visibility)
    }

    /**
     * A method to show/hide the back button of toolbar
     */
    fun showHideBack(visibility: Int = View.VISIBLE) {
        binding.ivLeft.visibility = visibility
    }

    fun setTitleTextView(title: String) {
        binding.tvTitle.text = title
    }

    fun setRightImageView(drawable: Int) {
        binding.ivRight.visibility = View.VISIBLE
        binding.ivRight.setImageDrawable(ContextCompat.getDrawable(this, drawable))
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.ivLeft -> onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Utility.hideKeyboard(this)
    }

    fun hasInternet(): Boolean {
        return Utility.hasInternet(this)
    }

    fun showLoading(isLoading: Boolean) {
        if (isLoading) showLoading()
        else hideLoading()
    }

    fun showLoading() {
        if (progressDialog == null) {
            progressDialog = Dialog(this, R.style.CustomDialog)
        } else {
            return
        }
        val view = LayoutInflater.from(this).inflate(R.layout.app_loading_dialog, null, false)
        progressDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        progressDialog?.setContentView(view)
        progressDialog?.window?.setBackgroundDrawable(ContextCompat.getDrawable(this, android.R.color.transparent))
        progressDialog?.setCancelable(false)
        progressDialog?.setCanceledOnTouchOutside(false)
        progressDialog?.show()
    }

    fun hideLoading() {
        if (progressDialog != null) {
            progressDialog?.dismiss()
            progressDialog = null
        }
    }

    fun showToolbarLoading() {
        binding.progressView.visibility = View.VISIBLE
    }

    fun hideToolbarLoading() {
        binding.progressView.visibility = View.GONE
    }


    open fun onInternetFailure(message: Int) {
        Utility.showSnackBar(binding.layoutContainer, getString(message))
    }

    fun onAuthenticationFailure() {
        Utility.logoutUser(this)
    }

}