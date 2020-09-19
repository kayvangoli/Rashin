package com.k1apps.rashintestapp.infrastracture.db

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.k1apps.rashintestapp.infrastracture.content.ContentResponse

@Dao
interface ContentDao {
    @Query("SELECT * FROM ContentResponse ORDER BY createDate DESC")
    fun getContents(): DataSource.Factory<Int, ContentResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(contentList: List<ContentResponse>)

    @Update
    fun update(contentResponse: ContentResponse)

    @Query("SELECT * FROM ContentResponse WHERE id == :id")
    fun getContentById(id: Int): LiveData<ContentResponse>

    @Query("SELECT * FROM ContentResponse WHERE favoriteStatus = 1 ORDER BY createDate DESC")
    fun getLikedContents(): DataSource.Factory<Int, ContentResponse>
}