package com.fastcampusmall.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.fastcampusmall.domain.model.Category
import com.fastcampusmall.domain.model.Product
import com.fastcampusmall.domain.usecase.CategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val useCase: CategoryUseCase,
) : ViewModel() {
    val categories = useCase.getCategories()

    private val _products = MutableStateFlow<List<Product>>(listOf())
    val products get() = _products.asStateFlow()

    suspend fun updateCategory(category: Category) {
        useCase.getProductsByCategory(category).collectLatest {
            _products.emit(it)
        }
    }
}