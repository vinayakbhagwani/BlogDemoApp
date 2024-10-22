package com.demo.domain.use_cases

import com.demo.common.Resource
import com.demo.domain.model.Blog
import com.demo.domain.repository.BlogsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBlogsUseCase @Inject constructor(private val getBlogsRepository: BlogsRepository) {

    operator fun invoke() : Flow<Resource<List<Blog>>> = flow {
        emit(Resource.Loading(null))
        try {
            val response = getBlogsRepository.getBlogs(limit = 30, skip = 0)
            emit(Resource.Success(data = response))
        } catch (e: Exception) {
            emit(Resource.Error("error occurred"))
        }
    }
}