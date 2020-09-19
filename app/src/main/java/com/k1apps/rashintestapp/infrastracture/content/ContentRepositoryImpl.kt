package com.k1apps.rashintestapp.infrastracture.content

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.k1apps.rashintestapp.repository.ContentRepository
import com.k1apps.rashintestapp.infrastracture.db.ContentDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContentRepositoryImpl(
    private val contentDao: ContentDao,
    private val boundaryCallback: ContentBoundaryCallback,
    private val scope: CoroutineScope
) : ContentRepository {
    override fun getContents(): LiveData<PagedList<ContentResponse>> {
        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setEnablePlaceholders(false)
            .build()
        return LivePagedListBuilder(contentDao.getContents(), config)
            .setBoundaryCallback(boundaryCallback).build()
    }

    override fun updateContent(contentResponse: ContentResponse) {
        scope.launch(Dispatchers.IO) {
            contentDao.update(contentResponse)
        }
    }

    override fun getLikedContents(): LiveData<PagedList<ContentResponse>> {
        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setEnablePlaceholders(false)
            .build()
        return LivePagedListBuilder(contentDao.getLikedContents(), config).build()
    }

    override fun getContentById(id: Int): LiveData<ContentResponse> {
        return contentDao.getContentById(id)
    }
}