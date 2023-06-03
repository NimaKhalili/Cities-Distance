package com.example.citiesdistance

import android.app.Application
import com.example.citiesdistance.data.repo.DistanceListRepository
import com.example.citiesdistance.data.repo.DistanceListRepositoryImpl
import com.example.citiesdistance.data.repo.DistanceRepository
import com.example.citiesdistance.data.repo.DistanceRepositoryImpl
import com.example.citiesdistance.data.repo.source.DistanceListLocalDataSource
import com.example.citiesdistance.data.repo.source.DistanceListRemoteDataSource
import com.example.citiesdistance.data.repo.source.DistanceLocalDataSource
import com.example.citiesdistance.data.repo.source.DistanceRemoteDataSource
import com.example.citiesdistance.feature.list.DistanceListViewModel
import com.example.citiesdistance.feature.home.HomeViewModel
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
            factory<DistanceListRepository> { DistanceListRepositoryImpl(DistanceListRemoteDataSource(get()), DistanceListLocalDataSource()) }
            viewModel { HomeViewModel(get()) }
            viewModel { DistanceListViewModel(get()) }
        }

        startKoin {
            androidContext(this@App)
            modules(myModules)
        }
    }
}