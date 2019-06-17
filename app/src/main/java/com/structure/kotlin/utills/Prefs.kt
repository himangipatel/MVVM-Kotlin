package com.structure.kotlin.utills

import android.content.Context
import android.content.SharedPreferences


/**
 * A singleton class which is used to store the login user's sharedPreferences.
 */
class Prefs private constructor(context: Context) {

    private val SP_NAME = "UserPref"
    private var sharedPreferences: SharedPreferences? = null

    init {
        sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
    }

    fun save(key: String, value: Boolean) {
        sharedPreferences!!.edit().putBoolean(key, value).apply()
    }

    fun save(key: String, value: String) {
        sharedPreferences!!.edit().putString(key, value).apply()
    }

    fun save(key: String, value: Int) {
        sharedPreferences!!.edit().putInt(key, value).apply()
    }

    fun save(key: String, value: Float) {
        sharedPreferences!!.edit().putFloat(key, value).apply()
    }

    fun save(key: String, value: Long) {
        sharedPreferences!!.edit().putLong(key, value).apply()
    }

    fun getBoolean(key: String, defValue: Boolean): Boolean {
        return sharedPreferences!!.getBoolean(key, defValue)
    }

    fun getString(key: String, defValue: String): String? {
        return sharedPreferences!!.getString(key, defValue)
    }

    fun getInt(key: String, defValue: Int): Int {
        return sharedPreferences!!.getInt(key, defValue)
    }

    fun getFloat(key: String, defValue: Float): Float {
        return sharedPreferences!!.getFloat(key, defValue)
    }

    fun getLong(key: String, defValue: Long): Long {
        return sharedPreferences!!.getLong(key, defValue)
    }

    fun getAll(): Map<String, *> {
        return sharedPreferences!!.getAll()
    }

    fun remove(key: String) {
        sharedPreferences!!.edit().remove(key).apply()
    }

    fun clearPrefs() {
        sharedPreferences!!.edit().clear().apply()
    }

    companion object {

        private var rmPrefs: Prefs? = null

        fun getInstance(context: Context): Prefs {
            return if (rmPrefs == null)
                Prefs(context)
            else rmPrefs as Prefs
        }
    }
}
