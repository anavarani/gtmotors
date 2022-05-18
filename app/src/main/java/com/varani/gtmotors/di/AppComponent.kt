package com.varani.gtmotors.di

import android.content.Context
import com.varani.gtmotors.MainActivity
import com.varani.gtmotors.di.module.NetworkModule
import com.varani.gtmotors.di.module.RepositoryModule
import com.varani.gtmotors.di.module.ViewModelModule
import com.varani.gtmotors.ui.search.SearchFragment
import com.varani.gtmotors.ui.searchfilter.SearchFilterFragment
import com.varani.gtmotors.ui.searchresults.SearchResultsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        ViewModelModule::class,
        RepositoryModule::class,
        NetworkModule::class
    ]
)
@Singleton
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: MainActivity)
    fun inject(searchFragment: SearchFragment)
    fun inject(searchFilterFragment: SearchFilterFragment)
    fun inject(searchResultsFragment: SearchResultsFragment)
}