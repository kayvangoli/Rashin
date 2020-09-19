package com.k1apps.rashintestapp.ui.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.k1apps.rashintestapp.NavGraphDirections
import com.k1apps.rashintestapp.R
import com.k1apps.rashintestapp.infrastracture.content.ContentResponse

class FavoritesAdapter(
    private val viewModel: FavoritesViewModel
) :
    PagedListAdapter<ContentResponse, ContentViewHolder>(ContentDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        return ContentViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                viewType, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        getItem(position)?.let {item ->
            holder.itemView.setOnClickListener {
                val direction = NavGraphDirections
                    .actionGlobalDetailFragment(item.id.toLong())
                holder.itemView.findNavController().navigate(direction)
            }
            holder.bind(item, viewModel)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_favorite
    }

}

class ContentViewHolder(
    private val binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(content: ContentResponse, viewModel: FavoritesViewModel) {
        binding.setVariable(BR.content, content)
        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()
    }
}

object ContentDiff : DiffUtil.ItemCallback<ContentResponse>() {


    override fun areItemsTheSame(oldItem: ContentResponse, newItem: ContentResponse) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: ContentResponse, newItem: ContentResponse): Boolean {
        return oldItem.title == newItem.title &&
                oldItem.thumbImage == newItem.thumbImage &&
                oldItem.favoriteStatus == newItem.favoriteStatus &&
                oldItem.zoneId == newItem.zoneId &&
                oldItem.createDate == newItem.createDate &&
                oldItem.id == newItem.id
    }
}

@BindingAdapter("selectView")
fun selectView(view: View, isSelected: Boolean) {
    view.isSelected = isSelected
}