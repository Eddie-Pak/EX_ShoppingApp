package com.fastcampusmall.presentation.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fastcampusmall.presentation.common.CustomAppBar
import com.fastcampusmall.presentation.navigation.AppNavHost
import com.fastcampusmall.presentation.common.CustomNavigationBar
import com.fastcampusmall.presentation.navigation.ScreenRouteDef
import com.fastcampusmall.presentation.navigation.ScreenTitle
import com.fastcampusmall.presentation.utils.getRouteTitle

@Composable
fun EntryPointScreen() {
    val snackBarHostState = remember { SnackbarHostState() }
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val currentTitle = navBackStackEntry?.getRouteTitle() ?: ScreenTitle.FAST_MALL

    val showBottomBarRoutes = listOf(
        ScreenRouteDef.Main::class.qualifiedName,
        ScreenRouteDef.CategoryMain::class.qualifiedName,
        ScreenRouteDef.MyPage::class.qualifiedName,
        ScreenRouteDef.Like::class.qualifiedName,
    )

    val isShowBottomBar = showBottomBarRoutes.any { route ->
        currentDestination?.route?.contains(route ?: "") == true
    }

    val showAppBarRoutes = listOf(
        ScreenRouteDef.Main::class.qualifiedName,
        ScreenRouteDef.CategoryMain::class.qualifiedName,
        ScreenRouteDef.MyPage::class.qualifiedName,
        ScreenRouteDef.Like::class.qualifiedName,
        ScreenRouteDef.Basket::class.qualifiedName,
        ScreenRouteDef.Search::class.qualifiedName
    )

    val isShowAppBar = showAppBarRoutes.any { route ->
        currentDestination?.route?.contains(route ?: "") == true
    }

    Scaffold(
        snackbarHost = { snackBarHostState },
        topBar = {
            if (isShowAppBar) {
                CustomAppBar(
                    title = currentTitle,
                    onSearchClick = {
                        navController.navigate(ScreenRouteDef.Search)
                    },
                    onBasketClick = {
                        navController.navigate(ScreenRouteDef.Basket)
                    }
                )
            }
        },
        bottomBar = {
            if (isShowBottomBar) {
                CustomNavigationBar(navController)
            }
        }
    ) { innerPadding ->
        AppNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}