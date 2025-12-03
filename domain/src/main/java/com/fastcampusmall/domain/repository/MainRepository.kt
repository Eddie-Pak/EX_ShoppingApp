package com.fastcampusmall.domain.repository

import com.fastcampusmall.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getProductList() : Flow<List<Product>>
}