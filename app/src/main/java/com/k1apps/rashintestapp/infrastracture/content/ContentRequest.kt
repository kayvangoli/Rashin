package com.k1apps.rashintestapp.infrastracture.content

data class ContentRequest(
    val requestType: Int,
    val pageSize: Int,
    val pageIndex: Int,
    val orderBy: String = "createdate",
    val order: String = "desc",
    val requestId: Int? = null
)

data class ContentsRequest(val request: ContentRequest)