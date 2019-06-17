package com.structure.kotlin.ui.userlisting

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import android.text.TextUtils.isEmpty
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.imagepicker.FilePickUtils
import com.structure.kotlin.R
import com.structure.kotlin.annotation.Layout
import com.structure.kotlin.base.MVVMActivity
import com.structure.kotlin.databinding.ActivityUserListingBinding
import com.structure.kotlin.listener.ItemClickListener
import com.structure.kotlin.model.User
import com.structure.kotlin.utills.Utility
import com.structure.kotlin.utills.onChange
import com.structure.kotlin.utills.removeFirstLastChar
import kotlinx.android.synthetic.main.activity_user_listing.*
import java.util.*
import android.view.WindowManager




@Layout(R.layout.activity_user_listing)
class UserListingActivity : MVVMActivity<UserListingViewModel>() {

    private var editableUser: User? = null
    lateinit var binding: ActivityUserListingBinding

    override fun createViewModel(): UserListingViewModel {
        return ViewModelProviders.of(this).get(UserListingViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val rotationAnimation = WindowManager.LayoutParams.ROTATION_ANIMATION_CROSSFADE
        val win = window
        val winParams = win.attributes
        winParams.rotationAnimation = rotationAnimation
        win.attributes = winParams

        binding = getBindingClass()


        val usersListingAdapter = UsersListingAdapter(itemClickListener)

        binding.layoutManager = LinearLayoutManager(this)
        binding.adapter = usersListingAdapter
        binding.dividerItemDecoration = DividerItemDecoration(
            this,
            LinearLayoutManager.VERTICAL
        )

        viewModel.getUserList()!!.observe(this, Observer {
            usersListingAdapter.submitList(it)
        })

        clickableViews(arrayOf(btnAdd))

        etEmail.onChange {
            Log.d("Edittetxt extension", it)
        }
    }


    override fun setRequestedOrientation(requestedOrientation: Int) {
        super.setRequestedOrientation(requestedOrientation)

        val rotationAnimation = WindowManager.LayoutParams.ROTATION_ANIMATION_JUMPCUT
        val win = window
        val winParams = win.attributes
        winParams.rotationAnimation = rotationAnimation
        win.attributes = winParams
    }

    private val itemClickListener = object : ItemClickListener<User> {
        override fun onClick(item: User, position: Int, view: View) {
            when (view.id) {
                R.id.ivDelete -> {
                    viewModel.deleteUser(item)
                }

                R.id.ivEdit -> {
                    editableUser = item
                    etEmail.setText(item.email)
                    etName.setText(item.fullName)
                    btnAdd.setText("Update")
                }
            }
        }
    }

    override fun onClick(v: View) {
        super.onClick(v)
        when (v.id) {
            R.id.btnAdd -> {
                if (isEmpty(Utility.getText(binding.etName)) || isEmpty(Utility.getText(binding.etEmail))) {
                    return
                }

                if (btnAdd.text.equals("Update")) {
                    editableUser!!.email = Utility.getText(binding.etEmail)
                    editableUser!!.fullName = Utility.getText(binding.etName)
                    viewModel.updateUser(editableUser!!)
                    binding.btnAdd.text = "Add"
                } else {
                    viewModel.insertUser(
                        Utility.getText(binding.etName),
                        Utility.getText(binding.etEmail)
                    )
                }
                binding.etName.setText("")
                binding.etEmail.setText("")
            }

            R.id.ivRight -> {
                showImagePickerDialog(onFileChoose)
            }
        }
    }

    private val onFileChoose = object : FilePickUtils.OnFileChoose {
        @Synchronized
        override fun onFileChoose(fileUri: String?, requestCode: Int, size: Int) {
            dismissImagePickerDialog()
            Log.d("File", fileUri)
            Glide.with(Objects.requireNonNull(this@UserListingActivity))
                .load(fileUri)
                .into(ivUser)
            ivUser.visibility = View.VISIBLE
        }
    }
}

