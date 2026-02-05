package com.fastcampusmall.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.fastcampusmall.domain.model.Product
import com.fastcampusmall.domain.usecase.ProductDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val useCase: ProductDetailUseCase
) : ViewModel() {
    private val _product = MutableStateFlow<Product?>(null)
    val product get() = _product.asStateFlow()

    suspend fun updateProduct(productId: String) {
        useCase.getProductDetail(productId).collectLatest {
            _product.emit(it)
        }
    }

    fun addCart(productId: String) {

    }
}