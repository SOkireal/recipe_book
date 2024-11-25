package com.example.recipebook.app

import android.app.Application
import com.example.recipebook.di.AppComponent
import com.example.recipebook.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
    }

    private fun initializeDagger() {
        appComponent = DaggerAppComponent.builder().build()
    }
}
