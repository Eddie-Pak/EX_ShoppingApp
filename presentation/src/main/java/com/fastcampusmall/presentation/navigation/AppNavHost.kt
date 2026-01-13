package com.fastcampusmall.presentation.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.fastcampusmall.presentation.ui.screen.category.CategoryMainScreen
import com.fastcampusmall.presentation.ui.screen.main.MainScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier,
    startDestination: ScreenRouteDef = ScreenRouteDef.MainGraph
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        navigation<ScreenRouteDef.MainGraph>(
            startDestination = ScreenRouteDef.Main
        ) {
            composable<ScreenRouteDef.Main> {
                MainScreen(modifier)
            }
        }

        navigation<ScreenRouteDef.CategoryGraph>(
            startDestination = ScreenRouteDef.CategoryMain
        ) {
            composable<ScreenRouteDef.CategoryMain> {
                CategoryMainScreen(modifier)
            }

            composable<ScreenRouteDef.CategoryDetail> {
                Text("CategoryDetail")
            }
        }

        navigation<ScreenRouteDef.MyPageGraph>(
            startDestination = ScreenRouteDef.MyPage
        ){
            composable<ScreenRouteDef.MyPage> {
                Text("MyPage")
            }
        }
    }
}