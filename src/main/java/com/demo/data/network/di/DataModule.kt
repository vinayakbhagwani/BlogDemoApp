package com.demo.data.network.di

import android.content.Context
import com.demo.common.Constant.BASE_URL
import com.demo.data.network.remote.ApiService
import com.demo.data.repository.BlogDetailRepoImpl
import com.demo.data.repository.BlogsRepositoryImpl
import com.demo.data.room.BlogDao
import com.demo.data.room.BlogDatabase
import com.demo.domain.repository.BlogDetailRepo
import com.demo.domain.repository.BlogsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideBlogsRepository(apiService: ApiService): BlogsRepository {
        return BlogsRepositoryImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) : BlogDatabase {
        return BlogDatabase.getInstance(context)
    }

    @Provides
    fun provideDao(blogDatabase: BlogDatabase): BlogDao {
        return blogDatabase.getBlogDao()
    }

    @Provides
    fun provideBlogDetailRepo(apiService: ApiService) : BlogDetailRepo {
        return BlogDetailRepoImpl(apiService)
    }
}