package com.example.citiesdistance

import android.app.Application
import com.example.citiesdistance.services.http.ApiService
import com.example.citiesdistance.services.http.createApiServiceInstance
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module


class App : Application() {
    override fun onCreate() {
        super.onCreate()

        val myModules = module {
            single<ApiService> { createApiServiceInstance() }
        }

        startKoin {
            androidContext(this@App)
            modules(myModules)
        }
    }
}