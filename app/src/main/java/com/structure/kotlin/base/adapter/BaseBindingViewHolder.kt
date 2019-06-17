package com.structure.kotlin.base.adapter

import androidx.databinding.ViewDataBinding
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import android.view.View

//Created by Himangi Patel on 23/11/18
class BaseBindingViewHolder(var binding: ViewDataBinding, @NonNull var clickListener: ClickListener) :
    RecyclerView.ViewHolder(binding.root), View.OnClickListener {


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    override fun onClick(v: View?) {
        if (v != null) {
            clickListener.onViewClick(adapterPosition, v)
        }
    }

    interface ClickListener {
        fun onViewClick(position: Int, view: View)
    }

}