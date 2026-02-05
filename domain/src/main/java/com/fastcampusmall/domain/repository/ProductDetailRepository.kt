package com.fastcampusmall.domain.repository

import com.fastcampusmall.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductDetailRepository {
    fun getProductDetail(productId: String): Flow<Product>
}