package com.structure.kotlin.base.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.structure.kotlin.listener.ItemClickListener


//Created by Himangi Patel on 23/11/18

abstract class BaseBindingAdapter<T> : RecyclerView.Adapter<BaseBindingViewHolder>(),
    BaseBindingViewHolder.ClickListener {

    protected var items: ArrayList<T> = ArrayList()

    var itemClickListener: ItemClickListener<T>? = null


    override fun onViewClick(position: Int, view: View) {
        itemClickListener?.onClick(items[position], position, view)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseBindingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = bind(inflater, parent, viewType)
        return BaseBindingViewHolder(binding, this)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItem(item: ArrayList<T>) {
        items = item
        notifyDataSetChanged()
    }

    fun addItems(item: ArrayList<T>) {
        items.addAll(item)
        notifyDataSetChanged()
    }

    fun addItem(item: T) {
        items.add(item)
        notifyDataSetChanged()
    }


    fun removeItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun clear() {
        val size = items.size
        if (size > 0) {
            items.clear()
            notifyItemRangeRemoved(0, size)
        }
    }

    fun setRecycleOnItemEventListener(mRecycleOnItemClickListener:ItemClickListener<T>): BaseBindingAdapter<T> {
        this.itemClickListener = mRecycleOnItemClickListener
        return this
    }

    protected abstract fun bind(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): ViewDataBinding

}