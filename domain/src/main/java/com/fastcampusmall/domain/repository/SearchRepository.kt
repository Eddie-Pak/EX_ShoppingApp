package com.fastcampusmall.domain.repository

import com.fastcampusmall.domain.model.Product
import com.fastcampusmall.domain.model.SearchKeyword
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun search(searchKeyword: SearchKeyword) : Flow<List<Product>>

    fun getSearchKeywords() : Flow<List<SearchKeyword>>
}