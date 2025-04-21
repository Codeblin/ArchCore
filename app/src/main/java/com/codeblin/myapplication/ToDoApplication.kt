package com.codeblin.myapplication

import android.app.Application
import com.codeblin.myapplication.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class ToDoApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        // Start Koin
        startKoin {
            androidContext(this@ToDoApplication) // Provide context
            modules(appModule) // Add modules
        }
    }
}