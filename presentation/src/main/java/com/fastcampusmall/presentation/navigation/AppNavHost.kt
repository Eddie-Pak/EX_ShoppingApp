package com.fastcampusmall.presentation.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

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
            Text("Main")
        }

        composable<ScreenRouteDef.Category> {
            Text("Category")
        }

        composable<ScreenRouteDef.MyPage> {
            Text("MyPage")
        }
    }
}