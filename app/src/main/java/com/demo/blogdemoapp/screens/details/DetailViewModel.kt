package com.demo.blogdemoapp.screens.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.common.Resource
import com.demo.domain.use_cases.GetBlogDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val blogDetailUseCase: GetBlogDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val _details = MutableStateFlow(BlogDetailsStateHolder())
    val details = _details.asStateFlow()

    init {
        savedStateHandle.getLiveData<String>("blogId").value?.let {
            getBlogDetails(id = it.toInt())
        }
    }

    private fun getBlogDetails(id: Int) {
        blogDetailUseCase(id).onEach {
            when(it) {
                is Resource.Error -> _details.emit(BlogDetailsStateHolder(error = it.message.toString()))
                is Resource.Loading -> _details.emit(BlogDetailsStateHolder(isLoading = true))
                is Resource.Success -> _details.emit(BlogDetailsStateHolder(data = it.data))
            }
        }.launchIn(viewModelScope)
    }

}