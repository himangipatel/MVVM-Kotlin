package com.structure.kotlin.ui.userlisting

import androidx.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import com.structure.kotlin.base.adapter.BaseBindingListAdapter
import com.structure.kotlin.base.adapter.BaseBindingViewHolder
import com.structure.kotlin.databinding.ItemAdapterSampleBinding
import com.structure.kotlin.listener.ItemClickListener
import com.structure.kotlin.model.User

/**
 * Created by Himangi on 11/1/19
 */
class UsersListingAdapter(val listener: ItemClickListener<User>) :
    BaseBindingListAdapter<User>(diffCallback = User.userItemCallback) {

    override fun bind(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): ViewDataBinding {
        return ItemAdapterSampleBinding.inflate(inflater, parent, false)
    }

    override fun onBindViewHolder(holder: BaseBindingViewHolder, position: Int) {
        val binding = holder.binding as ItemAdapterSampleBinding
        binding.setUser(getItem(position))
        binding.clicklistener = listener
        binding.position = position
    }

}