package com.fastcampusmall.presentation.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fastcampusmall.presentation.ui.screen.main.MainScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier,
    startDestination: ScreenRouteDef = ScreenRouteDef.Main
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<ScreenRouteDef.Main> {
            MainScreen(modifier)
        }

        composable<ScreenRouteDef.Category> {
            Text("Category")
        }

        composable<ScreenRouteDef.MyPage> {
            Text("MyPage")
        }
    }
}