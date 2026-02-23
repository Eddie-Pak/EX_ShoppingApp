package com.fastcampusmall.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fastcampusmall.domain.model.Product
import com.fastcampusmall.domain.usecase.BasketUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val useCase: BasketUseCase
) : ViewModel() {
    val basketProducts = useCase.getBasketProducts()

    fun removeBasketProduct(product: Product) {
        viewModelScope.launch {
            useCase.removeBasketProduct(product)
        }
    }
}