package com.k1apps.rashintestapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.k1apps.rashintestapp.infrastracture.content.ContentResponse
import com.k1apps.rashintestapp.repository.ContentRepository
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val contentRepo: ContentRepository) :
    ViewModel() {

    fun favoriteChanged(contentResponse: ContentResponse) {
        val newContent = contentResponse.copy()
        newContent.favoriteStatus = contentResponse.favoriteStatus.not()
        contentRepo.updateContent(newContent)
    }

    fun loadContentById(id: Long): LiveData<ContentResponse> {
        return contentRepo.getContentById(id.toInt())
    }
}