package com.example.hogwarts.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
        override fun onCreate() {
            super.onCreate()
            startKoin {
                androidLogger(org.koin.core.logger.Level.DEBUG)
                androidContext(this@App)
                modules(listOf(
                    viewModelModule,
                    repoModule,
                    persistenceModule,
                    apiModule,
                    retrofitModule
                ))
            }
        }
}
