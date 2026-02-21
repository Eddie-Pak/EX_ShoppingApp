package com.fastcampusmall.data.repository

import com.fastcampusmall.data.datasource.ProductDataSource
import com.fastcampusmall.data.db.dao.LikeProductDao
import com.fastcampusmall.data.db.entity.toLikeProductEntity
import com.fastcampusmall.domain.model.Category
import com.fastcampusmall.domain.model.Product
import com.fastcampusmall.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val dataSource: ProductDataSource,
    private val likeProductDao: LikeProductDao
) : CategoryRepository {
    override fun getCategories(): Flow<List<Category>> = flow {
        emit(
            listOf(
                Category.Top,
                Category.Dress,
                Category.OuterWear,
                Category.Pants,
                Category.Shoes,
                Category.Skirt,
                Category.Bag,
                Category.FashionAccessories
            )
        )
    }

    override fun getProductsByCategory(category: Category): Flow<List<Product>> {
        return dataSource.getHomeComponents().map { list ->
            list.filterIsInstance<Product>().filter { product ->
                product.category.categoryId == category.categoryId
            }
        }.combine(likeProductDao.getAll()) { products, likeList ->
            products.map { product ->
                updateLikeStatus(product, likeList.map { it.productId })
            }
        }
    }

    override suspend fun likeProduct(product: Product) {
        if (product.isLike) {
            likeProductDao.delete(product.productId)
        } else {
            likeProductDao.insert(product.toLikeProductEntity())
        }
    }

    private fun updateLikeStatus(product: Product, likeProductIds: List<String>) : Product {
        return product.copy(isLike = likeProductIds.contains(product.productId))
    }
}