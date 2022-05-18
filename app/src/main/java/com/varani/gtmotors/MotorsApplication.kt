package com.varani.gtmotors

import android.app.Application
import com.varani.gtmotors.di.AppComponent
import com.varani.gtmotors.di.DaggerAppComponent

class MotorsApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}