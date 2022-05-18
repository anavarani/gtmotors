package com.varani.gtmotors.di.module

import com.varani.gtmotors.domain.search.Repository
import com.varani.gtmotors.domain.search.SearchRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(searchRepository: SearchRepository): Repository
}