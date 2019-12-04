package com.structure.kotlin.utills

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar

object Utility {

    fun hasInternet(context: Context): Boolean {
        val connectivityManager = context.applicationContext
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnectedOrConnecting
    }

    fun logoutUser(context: Context?) {
        context?.let {
           Prefs.getInstance(context).clearPrefs()
        }
    }

    fun hideKeyboard(ctx: Activity) {
        if (ctx.currentFocus != null) {
            val imm = ctx.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(ctx.currentFocus!!.windowToken, 0)
        }
    }

    fun showSnackBar(v: View, msg: String) {
        Snackbar.make(v, msg, Snackbar.LENGTH_LONG).show()
    }



}