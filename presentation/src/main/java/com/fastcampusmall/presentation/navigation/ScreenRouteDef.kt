package com.fastcampusmall.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class ScreenRouteDef {
    @Serializable
    data object Main : ScreenRouteDef()

    @Serializable
    data object Category : ScreenRouteDef()

    @Serializable
    data object MyPage : ScreenRouteDef()
}