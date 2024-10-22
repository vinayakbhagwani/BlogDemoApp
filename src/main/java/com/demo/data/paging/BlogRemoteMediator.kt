package com.demo.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.demo.common.Resource
import com.demo.data.room.BlogDao
import com.demo.data.room.BlogKey
import com.demo.domain.model.Blog
import com.demo.domain.repository.BlogsRepository
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class BlogRemoteMediator @Inject constructor(
    private val initialPage: Int = 1,
    private val blogsRepo: BlogsRepository,
    private val blogDao: BlogDao
) : RemoteMediator<Int, Blog>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Blog>): MediatorResult {

        return try {
            val page: Int = when(loadType) {
                LoadType.APPEND -> {
                    val remoteKeys = getLastKey(state)
                    remoteKeys?.next ?: return MediatorResult.Success(true)
                }
                LoadType.PREPEND -> {
                    return MediatorResult.Success(true)
                }
                LoadType.REFRESH -> {
                    val remoteKeys = getCloseKey(state)
                    remoteKeys?.next?.minus(1) ?: initialPage
                }
            }

            val response = blogsRepo.getBlogs(skip = page*30 - 30, limit = state.config.pageSize)

            val endOfPagination = response.size < state.config.pageSize

            if (loadType == LoadType.REFRESH) {
                blogDao.deleteAllBlogKeys()
                blogDao.deleteAllItems()
            }

            val prev = if (page == initialPage) initialPage else page - 1
            val next = if (endOfPagination) null else page + 1

            val listOfBlogKeys = response.map {
                BlogKey(it.id, prev, next)
            }

            blogDao.insertAllBlogKeys(listOfBlogKeys)
            blogDao.insertAllBlogs(response)

            if(endOfPagination){
                MediatorResult.Success(true)
            } else {
                MediatorResult.Success(false)
            }

        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getLastKey(state: PagingState<Int, Blog>): BlogKey? {
        return state.lastItemOrNull()?.let {
            blogDao.getAllKeys(it.id)
        }
    }

    private suspend fun getCloseKey(state: PagingState<Int, Blog>): BlogKey? {
        return state.anchorPosition?.let {
            state.closestItemToPosition(it)?.let {
                blogDao.getAllKeys(it.id)
            }
        }
    }
}