package com.fastcampusmall.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fastcampusmall.domain.model.Product
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
    private val _searchResult = MutableStateFlow<List<Product>>(emptyList())
    val searchResult = _searchResult.asStateFlow()
    val searchKeywords = useCase.getSearchKeywords()

    fun search(keyword: String) {
        viewModelScope.launch {
            useCase.search(SearchKeyword(keyword)).collectLatest {
                _searchResult.emit(it)
            }
        }
    }
}