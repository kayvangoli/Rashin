package com.k1apps.rashintestapp.infrastracture.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.k1apps.rashintestapp.infrastracture.content.ContentResponse

@Database(entities = [ContentResponse::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contentDao(): ContentDao
}