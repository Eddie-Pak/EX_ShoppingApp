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
    data object Main : ScreenRouteDef()

    @Serializable
    data object CategoryMain : ScreenRouteDef()
    @Serializable
    data object CategoryDetail : ScreenRouteDef()


    @Serializable
    data object MyPage : ScreenRouteDef()
}