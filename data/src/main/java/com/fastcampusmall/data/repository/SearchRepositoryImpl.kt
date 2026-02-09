package com.fastcampusmall.data.repository

import com.fastcampusmall.data.datasource.ProductDataSource
import com.fastcampusmall.data.db.dao.SearchDao
import com.fastcampusmall.data.db.entity.SearchKeywordEntity
import com.fastcampusmall.data.db.entity.toDomainModel
import com.fastcampusmall.domain.model.Product
import com.fastcampusmall.domain.model.SearchFilter
import com.fastcampusmall.domain.model.SearchKeyword
import com.fastcampusmall.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val productDataSource: ProductDataSource,
    private val searchDao: SearchDao,
) : SearchRepository {
    override suspend fun search(
        searchKeyword: SearchKeyword,
        filters: List<SearchFilter>,
    ): Flow<List<Product>> {
        searchDao.insert(SearchKeywordEntity(keyword = searchKeyword.keyword))

        return productDataSource.getProducts().map { list ->
            list.filter { isAvailableProduct(it, searchKeyword, filters) }
        }
    }

    private fun isAvailableProduct(
        product: Product,
        searchKeyword: SearchKeyword,
        filters: List<SearchFilter>,
    ): Boolean {
        var isAvailable = true

        filters.forEach {
            isAvailable = isAvailable && it.isAvailableProduct(product)
        }

        return isAvailable && product.productName.contains(searchKeyword.keyword)
    }

    override fun getSearchKeywords(): Flow<List<SearchKeyword>> {
        return searchDao.getAll().map { it.map { entity -> entity.toDomainModel() } }
    }
}