package com.fastcampusmall.domain.repository

import com.fastcampusmall.domain.model.BaseModel
import com.fastcampusmall.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getModelList() : Flow<List<BaseModel>>

    suspend fun likeProduct(product: Product)
}