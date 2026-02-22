package com.fastcampusmall.domain.usecase

import com.fastcampusmall.domain.model.Product
import com.fastcampusmall.domain.repository.LikeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LikeUseCase @Inject constructor(
    private val repository: LikeRepository
) {
    fun getLikedProducts(): Flow<List<Product>> {
        return repository.getLikedProducts()
    }

    suspend fun likeProduct(product: Product) {
        repository.likeProduct(product)
    }
}