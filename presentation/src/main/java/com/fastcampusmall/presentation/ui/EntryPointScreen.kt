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

@Composable
fun EntryPointScreen() {
    val snackBarHostState = remember { SnackbarHostState() }
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val showBottomBarRoutes = listOf(
        ScreenRouteDef.Main::class.qualifiedName,
        ScreenRouteDef.CategoryMain::class.qualifiedName,
        ScreenRouteDef.MyPage::class.qualifiedName
    )

    val isShowBottomBar = showBottomBarRoutes.any { route ->
        currentDestination?.route?.contains(route ?: "") == true
    }

    Scaffold(
        snackbarHost = { snackBarHostState },
        topBar = { CustomAppBar() },
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