// Safe here as method are used by Data binding
@file:Suppress("unused")

package com.structure.kotlin.utills

import androidx.databinding.BindingAdapter
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import android.text.Editable
import android.text.TextUtils.isEmpty
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.structure.kotlin.R

//
///**
// * Sets an adapter to a RecyclerView (to be used in view with one RecyclerView)
// * @param view the RecyclerView on which to set the adapter
// * @param adapter the adapter to set to the RecyclerView
// */
//@BindingAdapter("adapter")
//fun setAdapter(view: RecyclerView, adapter: PostAdapter) {
//    view.adapter = adapter
//}

/**
 * Sets a LayoutManager to a RecyclerView (to be used in view with one RecyclerView)
 * @param view the RecyclerView on which to set the LayoutManager
 * @param layoutManager the LayoutManager to set to the RecyclerView
 */
@BindingAdapter("layoutManager")
fun setLayoutManager(view: RecyclerView, layoutManager: RecyclerView.LayoutManager) {
    view.layoutManager = layoutManager
}

/**
 * Adds a DividerItemDecoration to a RecyclerView (to be used in view with one RecyclerView)
 * @param view the RecyclerView on which to set the DividerItemDecoration
 * @param dividerItemDecoration the DividerItemDecoration to set to the RecyclerView
 */
@BindingAdapter("dividerItemDecoration")
fun setDividerItemDecoration(view: RecyclerView, dividerItemDecoration: DividerItemDecoration) {
    view.addItemDecoration(dividerItemDecoration)
}


@BindingAdapter("imageURL")
fun loadImage(img: ImageView, imageUrl: String?) {
     if (!isEmpty(imageUrl)) {
         val requestOptions: RequestOptions by lazy {
             RequestOptions()
                 .placeholder(R.drawable.ic_launcher_background)
                 .transforms(CenterCrop())
         }
         Glide.with(img.context)
             .load(imageUrl)
             .apply(requestOptions)
             .into(img)
        }else {
            img.setImageDrawable(ContextCompat.getDrawable(img.getContext(),R.drawable.ic_launcher_background));
        }

}

fun EditText.onChange(cb: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            cb(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })

}


fun String.removeSpecialChar(cb: (String) -> Unit) {
    cb(this.replace("*/,.#@%^&()[]+-",""))
}

fun String.removeFirstLastChar():String {
    return this.substring(1, this.length - 1)
}