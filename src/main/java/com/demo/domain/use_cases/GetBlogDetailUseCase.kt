package com.demo.domain.use_cases

import com.demo.common.Resource
import com.demo.domain.model.Blog
import com.demo.domain.repository.BlogDetailRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBlogDetailUseCase @Inject constructor(private val blogDetailRepo: BlogDetailRepo) {

    operator fun invoke(id: Int): Flow<Resource<Blog>> = flow {
        emit(Resource.Loading(""))
        try {
            val response = blogDetailRepo.getBlogDetails(id)
            emit(Resource.Success(data = response))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }
}