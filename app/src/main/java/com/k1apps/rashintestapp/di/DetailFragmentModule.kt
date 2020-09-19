package com.k1apps.rashintestapp.di

import androidx.lifecycle.ViewModel
import com.k1apps.rashintestapp.ui.detail.DetailFragment
import com.k1apps.rashintestapp.ui.detail.DetailViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
internal abstract class DetailFragmentModule {
    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun detailFragment(): DetailFragment

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindSettingsViewModel(viewModel: DetailViewModel): ViewModel
}