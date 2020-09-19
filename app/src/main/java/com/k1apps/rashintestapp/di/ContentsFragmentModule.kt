package com.k1apps.rashintestapp.di

import androidx.lifecycle.ViewModel
import com.k1apps.rashintestapp.ui.content.ContentsFragment
import com.k1apps.rashintestapp.ui.content.ContentsViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
internal abstract class ContentsFragmentModule {
    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contentsFragment(): ContentsFragment

    @Binds
    @IntoMap
    @ViewModelKey(ContentsViewModel::class)
    abstract fun bindSettingsViewModel(viewModel: ContentsViewModel): ViewModel
}