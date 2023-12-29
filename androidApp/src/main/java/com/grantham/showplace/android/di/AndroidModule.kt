package com.grantham.showplace.android.di

import com.grantham.showplace.android.showDetails.ShowDetailsViewModel
import com.grantham.showplace.android.showFeed.ShowsViewModel
import com.grantham.showplace.di.getWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidModule = module {
    viewModel { ShowsViewModel(getWith("ShowsViewModel"), get()) }
    viewModel { ShowDetailsViewModel(getWith("ShowDetailsViewModel"), get(), get()) }
}