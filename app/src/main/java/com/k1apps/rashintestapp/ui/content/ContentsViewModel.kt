package com.k1apps.rashintestapp.ui.content

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.k1apps.rashintestapp.repository.ContentRepository
import com.k1apps.rashintestapp.infrastracture.content.ContentResponse
import javax.inject.Inject

class ContentsViewModel @Inject constructor(private val contentRepo: ContentRepository) :
    ViewModel() {
    val contents: LiveData<PagedList<ContentResponse>> = contentRepo.getContents()

    fun favoriteChanged(contentResponse: ContentResponse) {
        val newContent = contentResponse.copy()
        newContent.favoriteStatus = contentResponse.favoriteStatus.not()
        contentRepo.updateContent(newContent)
    }
}