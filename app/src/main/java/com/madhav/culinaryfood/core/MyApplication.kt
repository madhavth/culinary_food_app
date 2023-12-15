package com.madhav.culinaryfood.core

import android.app.Application

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override fun onTerminate() {
        super.onTerminate()
    }

    companion object {
        lateinit var instance: Application
            private set
    }
}