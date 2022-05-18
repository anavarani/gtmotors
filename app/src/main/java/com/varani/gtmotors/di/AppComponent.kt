package com.varani.gtmotors.di

import android.content.Context
import com.varani.gtmotors.MainActivity
import com.varani.gtmotors.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component

@Component(modules = [ViewModelModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: MainActivity)
}