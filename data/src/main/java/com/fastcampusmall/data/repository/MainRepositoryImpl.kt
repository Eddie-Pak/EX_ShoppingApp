package com.fastcampusmall.data.repository

import com.fastcampusmall.data.datasource.ProductDataSource
import com.fastcampusmall.data.db.dao.LikeProductDao
import com.fastcampusmall.data.db.entity.toLikeProductEntity
import com.fastcampusmall.domain.model.BaseModel
import com.fastcampusmall.domain.model.Carousel
import com.fastcampusmall.domain.model.Product
import com.fastcampusmall.domain.model.Ranking
import com.fastcampusmall.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val dataSource: ProductDataSource,
    private val likeProductDao: LikeProductDao
) : MainRepository {
    override fun getModelList(): Flow<List<BaseModel>> {
        return dataSource.getHomeComponents().combine(likeProductDao.getAll()) { homeComponents, likeList ->
            homeComponents.map { baseModel ->
                mappingLike(baseModel, likeList.map { it.productId })
            }
        }
    }

    override suspend fun likeProduct(product: Product) {
        if (product.isLike) {
            likeProductDao.delete(product.productId)
        } else {
            likeProductDao.insert(product.toLikeProductEntity().copy(isLike = true))
        }
    }

    private fun mappingLike(baseModel: BaseModel, likeProductIds: List<String>) : BaseModel {
        return when(baseModel) {
            is Carousel -> { baseModel.copy(productList = baseModel.productList.map { updateLikeStatus(it, likeProductIds) }) }
            is Ranking -> { baseModel.copy(productList = baseModel.productList.map { updateLikeStatus(it, likeProductIds) }) }
            is Product -> { updateLikeStatus(baseModel, likeProductIds) }
            else -> baseModel
        }
    }

    private fun updateLikeStatus(product: Product, likeProductIds: List<String>) : Product {
        return product.copy(isLike = likeProductIds.contains(product.productId))
    }
}