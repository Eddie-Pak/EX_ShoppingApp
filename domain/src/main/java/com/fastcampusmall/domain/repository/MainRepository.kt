package com.fastcampusmall.domain.repository

import com.fastcampusmall.domain.model.BaseModel
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getModelList() : Flow<List<BaseModel>>
}