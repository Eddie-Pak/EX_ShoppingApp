package com.fastcampusmall.data.repository

import com.fastcampusmall.data.datasource.ProductDataSource
import com.fastcampusmall.data.db.dao.SearchDao
import com.fastcampusmall.data.db.entity.SearchKeywordEntity
import com.fastcampusmall.data.db.entity.toDomainModel
import com.fastcampusmall.domain.model.Product
import com.fastcampusmall.domain.model.SearchKeyword
import com.fastcampusmall.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val productDataSource: ProductDataSource,
    private val searchDao: SearchDao
) : SearchRepository {
    override suspend fun search(searchKeyword: SearchKeyword): Flow<List<Product>> {
        searchDao.insert(SearchKeywordEntity(keyword = searchKeyword.keyword))
        return productDataSource.getProducts().map { list ->
            list.filter { it.productName.contains(searchKeyword.keyword) }
        }
    }

    override fun getSearchKeywords(): Flow<List<SearchKeyword>> {
        return searchDao.getAll().map { it.map { entity -> entity.toDomainModel() } }
    }
}