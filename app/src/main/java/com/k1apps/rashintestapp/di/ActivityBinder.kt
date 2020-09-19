package com.k1apps.rashintestapp.di

import com.k1apps.rashintestapp.ui.MainActivity
import com.k1apps.rashintestapp.ui.favorite.FavoritesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBinder {
    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            ContentsFragmentModule::class,
            DetailFragmentModule::class,
            FavoritesFragmentModule::class
        ]
    )
    internal abstract fun mainActivity(): MainActivity
}

