package com.structure.kotlin.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by Himangi on 11/1/19
 */
/**
 * Created by Himangi on 9/1/19
 */
abstract class BaseBindingListAdapter<I> protected constructor(diffCallback: DiffUtil.ItemCallback<I>) :
    ListAdapter<I, BaseBindingListAdapter.BaseBindingViewHolder>(diffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseBindingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = bind(inflater, parent, viewType)
        return BaseBindingViewHolder(binding)
    }

    protected abstract fun bind(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): ViewDataBinding


    public fun getItemData(position: Int): I {
        return getItem(position)
    }

    class BaseBindingViewHolder(var binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

}


//class CheckInAdapter(val listener: ItemClickListener<UserRegistrationData>) :
//    BaseBindingListAdapter<UserRegistrationData>(diffCallback = UserRegistrationData.callback) {
//
//
//    override fun bind(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): ViewDataBinding {
//        return ItemCheckinBinding.inflate(inflater, parent, false)
//    }
//
//    override fun onBindViewHolder(holder: BaseBindingViewHolder, position: Int) {
//
//    }
//
//}