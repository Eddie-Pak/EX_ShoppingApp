package com.fastcampusmall.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fastcampusmall.domain.model.Product
import com.fastcampusmall.domain.usecase.LikeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LikeViewModel @Inject constructor(
    private val useCase: LikeUseCase
) : ViewModel() {
    val likedProducts = useCase.getLikedProducts()

    fun likeProduct(product: Product) {
        viewModelScope.launch {
            useCase.likeProduct(product)
        }
    }
}