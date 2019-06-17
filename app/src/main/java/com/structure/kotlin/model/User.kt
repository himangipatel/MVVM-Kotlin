package com.structure.kotlin.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.recyclerview.widget.DiffUtil
import android.text.TextUtils.isEmpty
import android.view.View
import android.widget.Toast
import com.structure.kotlin.db.DatabaseHandler

/**
 * Created by Himangi on 8/1/19
 */
@Entity(tableName = "user", indices = [Index(value = ["email"], unique = true)])
class User(var fullName: String?, var email: String?) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    /*  var fullName: String = ""

      @ColumnInfo(name = "email")
      var email: String = ""


      constructor(fullName: String, email: String) : this() {
          this.fullName = fullName
          this.email = email
      }*/

    companion object {
        var userItemCallback: DiffUtil.ItemCallback<User> = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }


    fun deleteUser(view: View, user: User) {
        DatabaseHandler.getDatabase(view.context).userDao().delete(user)
    }



    fun displayUserData(view: View) {
        if (!isEmpty(fullName!!.trim { it <= ' ' })) {
            Toast.makeText(view.context, "$fullName $email", Toast.LENGTH_SHORT).show()
        }
    }


}
