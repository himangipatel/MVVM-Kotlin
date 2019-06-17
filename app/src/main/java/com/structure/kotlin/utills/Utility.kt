package com.structure.kotlin.utills

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Build
import com.google.android.material.snackbar.Snackbar
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView

import com.structure.kotlin.R


/**
 * Created by Himangi Patel on 26/3/18.
 */

/**
 * An object class which contains all the common utility methods used in whole app.
 */
object Utility {

    /**
     * A method which returns the state of internet connectivity of user's phone.
     */
    fun hasInternet(context: Context): Boolean {
        val connectivityManager = context.applicationContext
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnectedOrConnecting
    }

    /**
     *   Description: This method is used to get EditText value after trimming the content.
     *   Created By: Dhiren Parmar
     */
    fun getText(editText: EditText): String {
        return editText.text.toString().trim()
        //return textView.text.toString().trim { it <= ' ' }
    }

    /**
     * A common method to logout the user and redirect to login screen
     */
    fun logoutUser(context: Context?) {
        context?.let {
           Prefs.getInstance(context).clearPrefs()
        }
    }

    /**
     * A method to show device keyboard for user input
     */
    fun showKeyboard(activity: Activity?, view: EditText) {
        try {
            val inputManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        } catch (e: Exception) {
            Log.e("Exception showKeyboard", e.toString())
        }
    }

    /**
     * A method to hide the device's keyboard
     */
    fun hideKeyboard(ctx: Activity) {
        if (ctx.currentFocus != null) {
            val imm = ctx.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(ctx.currentFocus!!.windowToken, 0)
        }
    }


    /**
     * A common method used in whole application to show a snack bar
     */
    fun showSnackBar(v: View, msg: String) {
        val mSnackBar = Snackbar.make(v, msg, Snackbar.LENGTH_LONG)
        val view = mSnackBar.view
        val params = view.layoutParams as FrameLayout.LayoutParams
        //  params.gravity = Gravity.TOP
        view.layoutParams = params
        view.setBackgroundColor(Color.DKGRAY)
        val mainTextView = view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        // val mainTextView: TextView = view.findViewById(android.support.design.R.id.snackbar_text)
        mainTextView.setTextColor(Color.WHITE)
        mainTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, v.context.resources.getDimension(R.dimen._8sdp))
        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            mainTextView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        }
        mainTextView.maxLines = 4
        //}
        mainTextView.gravity = Gravity.CENTER_HORIZONTAL
        mSnackBar.show()
    }



}