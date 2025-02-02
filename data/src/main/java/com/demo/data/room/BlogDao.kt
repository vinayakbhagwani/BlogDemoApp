package com.demo.data.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demo.domain.model.Blog

@Dao
interface BlogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBlogs(list: List<Blog>)

    @Query("SELECT * FROM Blog")
    fun getAllBlogItems(): PagingSource<Int, Blog>

    @Query("DELETE FROM Blog")
    suspend fun deleteAllItems()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBlogKeys(list: List<BlogKey>)

    @Query("DELETE FROM BlogKey")
    suspend fun deleteAllBlogKeys()

    @Query("SELECT * FROM BlogKey WHERE id=:id")
    suspend fun getAllKeys(id: Int): BlogKey

}