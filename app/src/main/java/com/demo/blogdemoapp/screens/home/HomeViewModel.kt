package com.demo.blogdemoapp.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.demo.common.Resource
import com.demo.data.paging.BlogRemoteMediator
import com.demo.data.room.BlogDao
import com.demo.domain.repository.BlogsRepository
import com.demo.domain.use_cases.GetBlogsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getBlogsUseCase: GetBlogsUseCase,
    private val blogsRepository: BlogsRepository, private val blogDao: BlogDao) :
    ViewModel() {

    private val _blogs = MutableStateFlow(HomeState())
    val blogs: StateFlow<HomeState> = _blogs.asStateFlow()

    @OptIn(ExperimentalPagingApi::class)
    val pager = Pager(
        config = PagingConfig(pageSize = 30, prefetchDistance = 5),
        remoteMediator = BlogRemoteMediator(blogsRepo = blogsRepository, blogDao = blogDao)
    ) {
        blogDao.getAllBlogItems()
    }.flow.cachedIn(viewModelScope)

    init {
//        getBlogs()
    }

    private fun getBlogs() {
        getBlogsUseCase().onEach {
            when (it) {
                is Resource.Error -> {
                    _blogs.emit(HomeState().copy(error = it.message ?: ""))
                }

                is Resource.Loading -> {
                    _blogs.emit(HomeState().copy(isLoading = true))
                }

                is Resource.Success -> {
                    _blogs.emit(HomeState().copy(data = it.data))
                }
            }
        }.launchIn(viewModelScope)
    }

}