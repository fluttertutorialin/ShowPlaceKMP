package com.grantham.showplace.android

import android.app.Application
import com.grantham.showplace.android.di.androidModule
import com.grantham.showplace.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger


class ShowPlaceApp : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger()
            androidContext(this@ShowPlaceApp)
            modules(androidModule)
        }
    }
}