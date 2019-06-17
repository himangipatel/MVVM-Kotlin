/*
 * Copyright (c) 2016.
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package com.structure.kotlin.base



import androidx.fragment.app.Fragment
import android.view.View
import com.structure.kotlin.utills.Utility


abstract class BaseFragment : Fragment() {


    fun hasInternet(): Boolean {
        return Utility.hasInternet(this.activity!!)
    }


    protected fun clickableViews(views: Array<View>) {
        (activity as BaseActivity).clickableViews(views)
    }

    /**
     * A method to show progress dialog on center of screen during api calls
     */
    open fun showLoading() {
        (activity as BaseActivity).showLoading()
    }

    /**
     * A method to hide the progress dialog
     */
    open fun hideLoading() {
        (activity as BaseActivity).hideLoading()
    }


    /**
     * A method to show horizontal progress bar on toolbar during api calls
     */
    open fun showToolbarLoading() {
        (activity as BaseActivity).showToolbarLoading()
    }

    /**
     * A method to hide the  horizontal progress bar
     */
    open fun hideToolbarLoading() {
        (activity as BaseActivity).hideToolbarLoading()
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
        Utility.logoutUser(context)
    }
}