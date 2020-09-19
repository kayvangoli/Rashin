package com.k1apps.rashintestapp.ui.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.k1apps.rashintestapp.ui.content.ContentsFragment
import com.k1apps.rashintestapp.ui.favorite.FavoritesFragment

class MainPagerAdapter(fragment: MainFragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            1 -> FavoritesFragment.newInstance()
            else -> ContentsFragment.newInstance()
        }
    }

}
