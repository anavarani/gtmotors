package com.varani.gtmotors.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.varani.gtmotors.di.ViewModelFactory
import com.varani.gtmotors.di.ViewModelKey
import com.varani.gtmotors.ui.search.SearchViewModel
import com.varani.gtmotors.ui.searchresults.SearchResultsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(mainViewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchResultsViewModel::class)
    abstract fun bindSearchResultsViewModel(mainViewModel: SearchResultsViewModel): ViewModel
}