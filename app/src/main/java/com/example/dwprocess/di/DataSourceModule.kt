package com.example.dwprocess.di

import com.example.dwprocess.repository.DWProcessRepository
import org.koin.dsl.module

val dataSourceModule = module {
    single { DWProcessRepository(get()) }
}