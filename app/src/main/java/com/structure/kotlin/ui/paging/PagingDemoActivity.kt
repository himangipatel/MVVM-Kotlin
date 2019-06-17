package com.structure.kotlin.ui.paging

import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.structure.kotlin.R
import com.structure.kotlin.annotation.Layout
import com.structure.kotlin.base.MVVMActivity
import com.structure.kotlin.databinding.ActivityPagingDemoBinding
import kotlinx.android.synthetic.main.activity_paging_demo.*

@Layout(R.layout.activity_paging_demo)
class PagingDemoActivity : MVVMActivity<PagingLibViewModel>() {

    override fun createViewModel(): PagingLibViewModel {
        return ViewModelProviders.of(this).get(PagingLibViewModel::class.java)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getBindingClass<ActivityPagingDemoBinding>()
        init()
    }

    private fun init() {

        list.layoutManager = LinearLayoutManager(this)
        val adapter = MyPageListAdapter()
        list.adapter = adapter
        list.showShimmerAdapter()

        viewModel.getListLiveData().observe(this, Observer {
            Handler().postDelayed({
                list.hideShimmerAdapter()
                adapter.submitList(it)
            }, 1000);
        })

//        viewModel.getProgressLoadStatus().observe(this, { status ->
//            /*  if (Objects.requireNonNull(status).equalsIgnoreCase(Constant.LOADING)) {
//                binding.progress.setVisibility(View.VISIBLE);
//            } else if (status.equalsIgnoreCase(Constant.LOADED)) {
//                binding.progress.setVisibility(View.GONE);
//            }*/
//        })

    }
}
