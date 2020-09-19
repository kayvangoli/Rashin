package com.k1apps.rashintestapp

import com.k1apps.rashintestapp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MyApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
         return DaggerAppComponent.factory().create(this)
    }

}