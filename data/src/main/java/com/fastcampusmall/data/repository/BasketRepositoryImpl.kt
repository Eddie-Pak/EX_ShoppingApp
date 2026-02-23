package com.fastcampusmall.data.repository

import com.fastcampusmall.data.db.dao.BasketProductDao
import com.fastcampusmall.data.db.entity.toDomainModel
import com.fastcampusmall.domain.model.BasketProduct
import com.fastcampusmall.domain.model.Product
import com.fastcampusmall.domain.repository.BasketRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BasketRepositoryImpl @Inject constructor(
    private val basketDao: BasketProductDao
) : BasketRepository {
    override fun getBasketProducts(): Flow<List<BasketProduct>> {
        return basketDao.getAll().map { list ->
            list.map { BasketProduct(it.toDomainModel(), it.count) }
        }
    }

    override suspend fun removeBasketProduct(product: Product) {
        return basketDao.delete(product.productId)
    }
}