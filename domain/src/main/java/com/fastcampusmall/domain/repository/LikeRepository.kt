package com.fastcampusmall.domain.repository

import com.fastcampusmall.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface LikeRepository {
    fun getLikedProducts(): Flow<List<Product>>
    suspend fun likeProduct(product: Product)
}