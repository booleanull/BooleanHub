package com.boolenull.booleanhub

import android.app.Application
import android.content.Context

class myApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {
        lateinit var context: Context
    }
}