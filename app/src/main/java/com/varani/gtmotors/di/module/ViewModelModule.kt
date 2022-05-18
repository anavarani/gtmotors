package com.varani.gtmotors.di.module

import androidx.lifecycle.ViewModelProvider
import com.varani.gtmotors.di.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}