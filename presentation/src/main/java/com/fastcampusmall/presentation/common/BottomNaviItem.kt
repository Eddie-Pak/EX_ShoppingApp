package com.fastcampusmall.presentation.common

import androidx.compose.ui.graphics.vector.ImageVector
import com.fastcampusmall.presentation.navigation.ScreenRouteDef

data class BottomNaviItem(
    val tabName: String,
    val icon: ImageVector,
    val route: ScreenRouteDef
)