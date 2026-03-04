package com.fastcampusmall.presentation.utils

import androidx.navigation.NavBackStackEntry
import com.fastcampusmall.presentation.navigation.ScreenTitle

fun NavBackStackEntry.getRouteTitle(): String {
    val routePath = destination.route ?: return ScreenTitle.FAST_MALL

    return when {
        routePath.contains("CategoryDetail") -> ScreenTitle.CATEGORY
        routePath.contains("CategoryMain") -> ScreenTitle.CATEGORY
        routePath.contains("ProductDetail") -> ScreenTitle.FAST_MALL
        routePath.contains("Main") -> ScreenTitle.HOME
        routePath.contains("MyPage") -> ScreenTitle.MY_PAGE
        routePath.contains("Like") -> ScreenTitle.LIKE
        routePath.contains("Search") -> ScreenTitle.SEARCH
        routePath.contains("Basket") -> ScreenTitle.BASKET
        else -> ScreenTitle.FAST_MALL
    }
}