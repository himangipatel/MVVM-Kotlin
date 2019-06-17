package com.structure.kotlin.ui.paging;

import androidx.paging.PagedListAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.structure.kotlin.R;
import com.structure.kotlin.databinding.RowLayoutBinding;

/**
 * Created by ${Saquib} on 12-08-2018.
 */

public class MyPageListAdapter extends PagedListAdapter<NewsModelClass, MyPageListAdapter.MyViewHolder> {


    protected MyPageListAdapter() {
        super(NewsModelClass.DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RowLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.row_layout, parent, false);

        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.binding.rlContent.setVisibility(View.VISIBLE);
        holder.binding.rlShimmer.setVisibility(View.GONE);
        holder.binding.setModel(getItem(position));
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        RowLayoutBinding binding;

        MyViewHolder(RowLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

    }
}
