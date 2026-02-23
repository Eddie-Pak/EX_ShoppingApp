package com.fastcampusmall.domain.usecase

import com.fastcampusmall.domain.model.BasketProduct
import com.fastcampusmall.domain.model.Product
import com.fastcampusmall.domain.repository.BasketRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BasketUseCase @Inject constructor(
    private val repository: BasketRepository
) {
    fun getBasketProducts(): Flow<List<BasketProduct>> {
        return repository.getBasketProducts()
    }

    suspend fun removeBasketProduct(product: Product) {
        repository.removeBasketProduct(product)
    }
}