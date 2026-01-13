package com.fastcampusmall.domain.repository

import com.fastcampusmall.domain.model.Category
import com.fastcampusmall.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategories(): Flow<List<Category>>
    fun getProductsByCategory(category: Category): Flow<List<Product>>
}