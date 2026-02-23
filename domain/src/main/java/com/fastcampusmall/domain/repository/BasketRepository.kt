package com.fastcampusmall.domain.repository

import com.fastcampusmall.domain.model.BasketProduct
import com.fastcampusmall.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface BasketRepository {
    fun getBasketProducts(): Flow<List<BasketProduct>>

    suspend fun removeBasketProduct(product: Product)
}