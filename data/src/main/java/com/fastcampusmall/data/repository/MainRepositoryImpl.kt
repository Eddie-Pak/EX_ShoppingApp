package com.fastcampusmall.data.repository

import com.fastcampusmall.data.datasource.ProductDataSource
import com.fastcampusmall.domain.model.BaseModel
import com.fastcampusmall.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val dataSource: ProductDataSource
) : MainRepository {
    override fun getModelList(): Flow<List<BaseModel>> {
        return dataSource.getHomeComponents()
    }
}