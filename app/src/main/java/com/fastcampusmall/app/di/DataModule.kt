package com.fastcampusmall.app.di

import com.fastcampusmall.data.repository.CategoryRepositoryImpl
import com.fastcampusmall.data.repository.MainRepositoryImpl
import com.fastcampusmall.domain.repository.CategoryRepository
import com.fastcampusmall.domain.repository.MainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindMainRepository(mainRepositoryImpl: MainRepositoryImpl) : MainRepository

    @Binds
    @Singleton
    fun bindCategoryRepository(categoryRepositoryImpl: CategoryRepositoryImpl) : CategoryRepository
}