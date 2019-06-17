package com.structure.kotlin.listener

import android.view.View

/**
 * Created by Himangi Patelon 4/6/18.
 */


/**
 * An interface class which is used to get callback in an activity
 * , whenever an adapter item is clicked by user.
 */
interface ItemClickListener<T> {
    public fun onClick(item: T, position: Int, view: View)
}