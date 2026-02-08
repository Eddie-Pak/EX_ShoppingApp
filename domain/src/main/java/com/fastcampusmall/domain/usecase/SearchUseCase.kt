package com.fastcampusmall.domain.usecase

import com.fastcampusmall.domain.model.Product
import com.fastcampusmall.domain.model.SearchKeyword
import com.fastcampusmall.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend fun search(keyword: SearchKeyword) : Flow<List<Product>> {
        return searchRepository.search(keyword)
    }

    fun getSearchKeywords() : Flow<List<SearchKeyword>> {
        return searchRepository.getSearchKeywords()
    }
}