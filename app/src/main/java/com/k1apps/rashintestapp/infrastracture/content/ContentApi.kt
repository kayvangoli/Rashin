package com.k1apps.rashintestapp.infrastracture.content

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ContentApi {
    @POST("/mobile/request.asmx/GetContentList")
    suspend fun loadContents(
        @Body contentsRequest: ContentsRequest
    ): Response<ContentsResponse>
}