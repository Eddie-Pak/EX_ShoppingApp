package com.fastcampusmall.presentation.navigation

import com.fastcampusmall.domain.model.Category
import kotlinx.serialization.Serializable

@Serializable
sealed class ScreenRouteDef {
    @Serializable
    data object MainGraph : ScreenRouteDef()

    @Serializable
    data object CategoryGraph : ScreenRouteDef()

    @Serializable
    data object MyPageGraph : ScreenRouteDef()

    @Serializable
    data object Main : ScreenRouteDef()

    @Serializable
    data object CategoryMain : ScreenRouteDef()
    @Serializable
    data class CategoryDetail(val category: Category) : ScreenRouteDef()

    @Serializable
    data object MyPage : ScreenRouteDef()

    @Serializable
    data class ProductDetail(val productId: String) : ScreenRouteDef()
}