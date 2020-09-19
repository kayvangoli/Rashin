package com.k1apps.rashintestapp.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.k1apps.rashintestapp.repository.ContentRepository
import com.k1apps.rashintestapp.infrastracture.content.ContentResponse
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(private val contentRepo: ContentRepository) :
    ViewModel() {
    val contents: LiveData<PagedList<ContentResponse>> = contentRepo.getLikedContents()

    fun favoriteChanged(contentResponse: ContentResponse) {
        val newContent = contentResponse.copy()
        newContent.favoriteStatus = contentResponse.favoriteStatus.not()
        contentRepo.updateContent(newContent)
    }
}