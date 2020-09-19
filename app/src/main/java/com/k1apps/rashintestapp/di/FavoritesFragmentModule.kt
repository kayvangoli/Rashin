package com.k1apps.rashintestapp.di

import androidx.lifecycle.ViewModel
import com.k1apps.rashintestapp.ui.favorite.FavoritesFragment
import com.k1apps.rashintestapp.ui.favorite.FavoritesViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
internal abstract class FavoritesFragmentModule {
    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contentsFragment(): FavoritesFragment

    @Binds
    @IntoMap
    @ViewModelKey(FavoritesViewModel::class)
    abstract fun bindSettingsViewModel(viewModel: FavoritesViewModel): ViewModel
}