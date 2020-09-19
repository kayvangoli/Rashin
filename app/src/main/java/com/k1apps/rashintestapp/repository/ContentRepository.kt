package com.k1apps.rashintestapp.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.k1apps.rashintestapp.infrastracture.content.ContentResponse

interface ContentRepository {
    fun getContents(): LiveData<PagedList<ContentResponse>>
    fun updateContent(contentResponse: ContentResponse)
    fun getContentById(id: Int): LiveData<ContentResponse>
    fun getLikedContents(): LiveData<PagedList<ContentResponse>>
}