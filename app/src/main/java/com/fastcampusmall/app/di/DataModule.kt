package com.fastcampusmall.app.di

import com.fastcampusmall.data.deserializer.BaseModelDeserializer
import com.fastcampusmall.data.deserializer.CategoryDeserializer
import com.fastcampusmall.data.repository.CategoryRepositoryImpl
import com.fastcampusmall.data.repository.MainRepositoryImpl
import com.fastcampusmall.domain.model.BaseModel
import com.fastcampusmall.domain.model.Category
import com.fastcampusmall.domain.repository.CategoryRepository
import com.fastcampusmall.domain.repository.MainRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
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

    companion object {
        @Provides
        @Singleton
        fun provideGson(): Gson {
            return GsonBuilder()
                .registerTypeAdapter(BaseModel::class.java, BaseModelDeserializer())
                .registerTypeAdapter(Category::class.java, CategoryDeserializer())
                .create()
        }
    }
}