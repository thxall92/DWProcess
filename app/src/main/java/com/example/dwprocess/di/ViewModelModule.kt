package com.example.dwprocess.di

import com.example.dwprocess.viewmodel.AccountListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AccountListViewModel(get()) }
}