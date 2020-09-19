package com.k1apps.rashintestapp.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.k1apps.rashintestapp.R
import com.k1apps.rashintestapp.databinding.FavoritesFragmentBinding
import com.k1apps.rashintestapp.infrastracture.content.ContentResponse
import com.k1apps.rashintestapp.infrastracture.util.viewModelProvider
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class FavoritesFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    companion object {
        fun newInstance() = FavoritesFragment()
    }

    private lateinit var viewModel: FavoritesViewModel
    private lateinit var binding: FavoritesFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = viewModelProvider(viewModelFactory)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FavoritesFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }
}

@BindingAdapter("contentItems", "viewModel")
fun contentItems(
    recyclerView: RecyclerView,
    contents: PagedList<ContentResponse>?,
    viewModel: FavoritesViewModel
) {
    if (recyclerView.adapter == null) {
        recyclerView.adapter = FavoritesAdapter(viewModel)
    }
    (recyclerView.adapter as FavoritesAdapter).apply {
        submitList(contents)
    }
}

@BindingAdapter("movieType")
fun movieType(tv: TextView, zoneId: Int) {
    tv.text = if (zoneId == 3) {
        tv.context.resources.getString(R.string.series)
    } else if (zoneId == 4) {
        tv.context.resources.getString(R.string.movie)
    } else {
        tv.context.resources.getString(R.string.unknown)
    }
}