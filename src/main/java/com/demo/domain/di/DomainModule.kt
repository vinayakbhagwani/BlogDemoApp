package com.demo.domain.di

import com.demo.domain.repository.BlogsRepository
import com.demo.domain.use_cases.GetBlogsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Provides
    fun provideGetBlogsUseCase(getBlogsRepository: BlogsRepository) : GetBlogsUseCase {
        return GetBlogsUseCase(getBlogsRepository)
    }

}