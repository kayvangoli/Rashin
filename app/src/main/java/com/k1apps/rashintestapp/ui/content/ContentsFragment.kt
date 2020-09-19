package com.k1apps.rashintestapp.ui.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.k1apps.rashintestapp.NavGraphDirections
import com.k1apps.rashintestapp.R
import com.k1apps.rashintestapp.databinding.ContentsFragmentBinding
import com.k1apps.rashintestapp.infrastracture.content.ContentResponse
import com.k1apps.rashintestapp.infrastracture.util.viewModelProvider
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ContentsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    companion object {
        fun newInstance() = ContentsFragment()
    }

    private lateinit var viewModel: ContentsViewModel
    private lateinit var binding: ContentsFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = viewModelProvider(viewModelFactory)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ContentsFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }
}

@BindingAdapter("contentItems", "viewModel")
fun contentItems(
    recyclerView: RecyclerView,
    contents: PagedList<ContentResponse>?,
    viewModel: ContentsViewModel
) {
    if (recyclerView.adapter == null) {
        recyclerView.adapter = ContentsAdapter(viewModel)
    }
    (recyclerView.adapter as ContentsAdapter).apply {
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