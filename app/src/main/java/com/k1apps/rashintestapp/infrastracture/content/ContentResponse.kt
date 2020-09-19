package com.k1apps.rashintestapp.infrastracture.content

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*


data class ContentsResponse(
    @SerializedName("Result") val result: ContentResult
)

data class ContentResult(
    @SerializedName("GetContentList") val contentList: List<ContentResponse>,
    @SerializedName("TotalPages") val totalPages: Int,
)

@Entity
data class ContentResponse(
    @SerializedName("ContentID") @PrimaryKey val id: Int,
    @SerializedName("Title") val title: String,
    @SerializedName("Summary") val summary: String,
    @SerializedName("ThumbImage") val thumbImage: String,
    @SerializedName("ZoneID") val zoneId: Int,
    @SerializedName("FavoriteStatus") var favoriteStatus: Boolean,
    @SerializedName("CreateDate") val createDate: Int,
)