package com.sandbox.reborn

import android.app.Application
import timber.log.Timber

class SandboxApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initTimber()
    }

    private fun initTimber() {
        // Always plant a debug tree as it's a test app
        Timber.plant(Timber.DebugTree())
    }
}