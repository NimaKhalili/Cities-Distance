package com.example.citiesdistance

import android.app.Application
import com.example.citiesdistance.data.repo.DistanceRepository
import com.example.citiesdistance.data.repo.DistanceRepositoryImpl
import com.example.citiesdistance.data.repo.source.DistanceLocalDataSource
import com.example.citiesdistance.data.repo.source.DistanceRemoteDataSource
import com.example.citiesdistance.feature.main.MainViewModel
import com.example.citiesdistance.services.http.ApiService
import com.example.citiesdistance.services.http.createApiServiceInstance
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        val myModules = module {
            single<ApiService> { createApiServiceInstance() }
            factory<DistanceRepository> { DistanceRepositoryImpl(DistanceRemoteDataSource(get()), DistanceLocalDataSource()) }
            viewModel { MainViewModel(get()) }
        }

        startKoin {
            androidContext(this@App)
            modules(myModules)
        }
    }
}