package com.fastcampusmall.domain.usecase

import com.fastcampusmall.domain.model.Product
import com.fastcampusmall.domain.repository.ProductDetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductDetailUseCase @Inject constructor(
    private val repository: ProductDetailRepository
) {
    fun getProductDetail(productId: String) : Flow<Product> {
        return repository.getProductDetail(productId)
    }
}