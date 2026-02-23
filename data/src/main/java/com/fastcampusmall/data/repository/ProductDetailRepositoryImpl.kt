package com.fastcampusmall.data.repository

import com.fastcampusmall.data.datasource.ProductDataSource
import com.fastcampusmall.data.db.dao.BasketProductDao
import com.fastcampusmall.data.db.entity.toBasketProductEntity
import com.fastcampusmall.domain.model.Product
import com.fastcampusmall.domain.repository.ProductDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductDetailRepositoryImpl @Inject constructor(
    private val dataSource: ProductDataSource,
    private val basketDao: BasketProductDao
) : ProductDetailRepository {
    override fun getProductDetail(productId: String): Flow<Product> {
        return dataSource.getHomeComponents().map { products ->
            products.filterIsInstance<Product>().first { it.productId == productId }
        }
    }

    override suspend fun addBasket(product: Product) {
        val alreadyExistProduct = basketDao.get(product.productId)
        if (alreadyExistProduct != null) {
            basketDao.insert(alreadyExistProduct.copy(count = alreadyExistProduct.count + 1))
        } else {
            basketDao.insert(product.toBasketProductEntity())
        }
    }
}