package com.kjk.lorempicsumapp

import android.app.Application
import timber.log.Timber

class LoremPicsumApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}