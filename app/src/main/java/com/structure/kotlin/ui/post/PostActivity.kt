package com.structure.kotlin.ui.post

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import com.structure.kotlin.R
import com.structure.kotlin.annotation.Layout
import com.structure.kotlin.base.MVVMActivity
import com.structure.kotlin.databinding.ActivityPostBinding
import com.structure.kotlin.ui.userlisting.UserListingActivity

@Layout(R.layout.activity_post)
class PostActivity : MVVMActivity<PostViewModel>() {

    override fun createViewModel(): PostViewModel {
        return ViewModelProviders.of(this).get(PostViewModel::class.java);
    }

    lateinit var activityPostBinding: ActivityPostBinding
    private val postsAdapter = PostAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityPostBinding = getBindingClass()
        showHideBack(View.VISIBLE)
        setTitleTextView("Post")
        activityPostBinding.adapter = postsAdapter
        activityPostBinding.layoutManager = LinearLayoutManager(this)
        activityPostBinding.dividerItemDecoration = DividerItemDecoration(
            this,
            LinearLayoutManager.VERTICAL
        )

        viewModel.getUserPost()?.observe(this, Observer {
            it?.let { it1 -> postsAdapter.updatePosts(it1) }
        })
    }

    override fun onClick(v: View) {
        super.onClick(v)
        when (v.id) {
            R.id.ivRight -> {
//                postsAdapter.clearPosts()
//                Handler().postDelayed(Runnable {
//                    postsAdapter.updatePosts(viewModel.getUserPost()!!.value!!)
//                }, 5000)

                val intent = Intent(this@PostActivity, UserListingActivity::class.java)
                startActivity(intent)
            }
        }

    }

}