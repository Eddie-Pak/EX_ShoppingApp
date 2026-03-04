package com.fastcampusmall.presentation.navigation

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
    data object LikeGraph : ScreenRouteDef()

    @Serializable
    data object Main : ScreenRouteDef()

    @Serializable
    data object CategoryMain : ScreenRouteDef()
    @Serializable
    data class CategoryDetail(val categoryId: String) : ScreenRouteDef()

    @Serializable
    data object MyPage : ScreenRouteDef()

    @Serializable
    data object Like : ScreenRouteDef()

    @Serializable
    data class ProductDetail(val productId: String) : ScreenRouteDef()

    @Serializable
    data object Search : ScreenRouteDef()

    @Serializable
    data object Basket : ScreenRouteDef()
}