package com.fastcampusmall.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fastcampusmall.domain.model.Product
import com.fastcampusmall.domain.model.SearchFilter
import com.fastcampusmall.domain.model.SearchKeyword
import com.fastcampusmall.domain.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCase: SearchUseCase,
) : ViewModel() {
    private val searchManager = SearchManager()

    private val _searchResult = MutableStateFlow<List<Product>>(emptyList())
    val searchResult get() = _searchResult.asStateFlow()

    val searchKeywords = useCase.getSearchKeywords()
    val searchFilters = searchManager.filters

    fun search(keyword: String) {
        viewModelScope.launch {
            searchInternalNewSearchKeyword(keyword)
        }
    }

    fun updateFilter(filter: SearchFilter) {
        viewModelScope.launch {
            searchManager.updateFilter(filter)

            searchInternal()
        }
    }

    fun likeProduct(product: Product) {
        viewModelScope.launch {
            useCase.likeProduct(product)
        }
    }

    private suspend fun searchInternal() {
        useCase.search(searchManager.searchKeyword, searchManager.currentFilters()).collectLatest {
            _searchResult.emit(it)
        }
    }

    private suspend fun searchInternalNewSearchKeyword(newSearchKeyword: String = "") {
        searchManager.clearFilter()

        useCase.search(SearchKeyword(newSearchKeyword), searchManager.currentFilters()).collectLatest {
            searchManager.initSearchManager(newSearchKeyword, it)

            _searchResult.emit(it)
        }
    }
}