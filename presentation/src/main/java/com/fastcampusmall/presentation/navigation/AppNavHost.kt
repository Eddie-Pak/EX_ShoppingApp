package com.fastcampusmall.presentation.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.fastcampusmall.domain.model.Category
import com.fastcampusmall.presentation.common.parcelableType
import com.fastcampusmall.presentation.ui.screen.category.CategoryDetailScreen
import com.fastcampusmall.presentation.ui.screen.category.CategoryMainScreen
import com.fastcampusmall.presentation.ui.screen.main.MainScreen
import kotlin.reflect.typeOf

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
                CategoryMainScreen(modifier) { category ->
                    navController.navigate(ScreenRouteDef.CategoryDetail(category))
                }
            }

            composable<ScreenRouteDef.CategoryDetail>(
                typeMap = mapOf(typeOf<Category>() to parcelableType<Category>())
            ) { backStackEntry ->
                val category = backStackEntry.toRoute<ScreenRouteDef.CategoryDetail>()

                CategoryDetailScreen(modifier = modifier, category = category.category)
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