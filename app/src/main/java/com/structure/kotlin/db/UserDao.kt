package com.structure.kotlin.db

import androidx.lifecycle.LiveData
import androidx.room.*
import android.database.Cursor
import com.structure.kotlin.model.User

/**
 * Created by Himangi on 8/1/19
 */
@Dao
interface UserDao {

    @get:Query("SELECT * FROM user")
    val allUsers: LiveData<List<User>>

    @get:Query("SELECT * FROM user")
    val userCursor: Cursor


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repo: User)

    @Insert
    fun insert(repoList: List<User>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(vararg repos: User)

    @Delete
    fun delete(vararg repos: User)

    @Query("SELECT * FROM user WHERE id=:id")
    fun getUser(id: Int): User

    @Query("SELECT * FROM user WHERE fullName=:name")
    fun getUsersByName(name: String): List<User>

    @Query("SELECT * FROM user WHERE fullName=:name LIMIT :max")
    fun getUsersByName(max: Int, vararg name: String): List<User>

}