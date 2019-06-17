package com.structure.kotlin.base

import android.app.Application
import com.facebook.stetho.Stetho


class AppClass :Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        app = this
    }

    companion object{
        private lateinit var app: AppClass
        fun app(): AppClass {
            return app
        }
    }

}