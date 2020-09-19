package com.k1apps.rashintestapp.infrastracture.content

import androidx.paging.PagedList
import com.k1apps.rashintestapp.infrastracture.db.ContentDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ContentBoundaryCallback(
    private val scope: CoroutineScope,
    private val networkApi: ContentApi,
    private val contentDao: ContentDao
) : PagedList.BoundaryCallback<ContentResponse>() {
    private var pageIndex = 1
    override fun onZeroItemsLoaded() {
        loadAndSave()
    }

    private fun loadAndSave() {
        GlobalScope.launch(Dispatchers.IO) {
            launch {
                val request = ContentRequest(2, 30, pageIndex)
                val response = networkApi.loadContents(ContentsRequest(request))
                when {
                    response.isSuccessful -> {
                        response.body()?.let {
                            contentDao.insertAll(it.result.contentList)
                        }
                    }
                }
            }
        }
    }

    override fun onItemAtFrontLoaded(itemAtFront: ContentResponse) {
    }

    override fun onItemAtEndLoaded(itemAtEnd: ContentResponse) {
        pageIndex++
        loadAndSave()
    }
}