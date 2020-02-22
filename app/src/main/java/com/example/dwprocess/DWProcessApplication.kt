package com.example.dwprocess

import android.app.Application
import com.example.dwprocess.di.dataSourceModule
import com.example.dwprocess.di.networkModule
import com.example.dwprocess.di.viewModelModule
import org.koin.core.context.startKoin

class DWProcessApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin {
            printLogger()
            modules(listOf(
                networkModule,
                dataSourceModule,
                viewModelModule
            ))
        }
    }

}