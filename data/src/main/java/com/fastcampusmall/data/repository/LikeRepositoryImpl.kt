package com.fastcampusmall.data.repository

import com.fastcampusmall.data.db.dao.LikeProductDao
import com.fastcampusmall.data.db.entity.toDomainModel
import com.fastcampusmall.data.db.entity.toLikeProductEntity
import com.fastcampusmall.domain.model.Product
import com.fastcampusmall.domain.repository.LikeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LikeRepositoryImpl @Inject constructor(
    private val likeProductDao: LikeProductDao
) : LikeRepository {
    override fun getLikedProducts(): Flow<List<Product>> {
        return likeProductDao.getAll().map { entities ->
            entities.map { it.toDomainModel() }
        }
    }

    override suspend fun likeProduct(product: Product) {
        if (product.isLike) {
            likeProductDao.delete(product.productId)
        } else {
            likeProductDao.insert(product.toLikeProductEntity().copy(isLike = true))
        }
    }
}