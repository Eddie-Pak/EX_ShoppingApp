package com.fastcampusmall.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import com.fastcampusmall.presentation.ui.screen.basket.BasketScreen
import com.fastcampusmall.presentation.ui.screen.category.CategoryDetailScreen
import com.fastcampusmall.presentation.ui.screen.category.CategoryMainScreen
import com.fastcampusmall.presentation.ui.screen.detail.ProductDetailScreen
import com.fastcampusmall.presentation.ui.screen.like.LikeScreen
import com.fastcampusmall.presentation.ui.screen.main.MainScreen
import com.fastcampusmall.presentation.ui.screen.mypage.MyPageScreen
import com.fastcampusmall.presentation.ui.screen.search.SearchScreen

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
                MainScreen(
                    modifier = modifier,
                ) { productId ->
                    navController.navigate(ScreenRouteDef.ProductDetail(productId))
                }
            }
        }

        navigation<ScreenRouteDef.CategoryGraph>(
            startDestination = ScreenRouteDef.CategoryMain
        ) {
            composable<ScreenRouteDef.CategoryMain> {
                CategoryMainScreen(modifier) { category ->
                    navController.navigate(ScreenRouteDef.CategoryDetail(category.categoryId))
                }
            }

            composable<ScreenRouteDef.CategoryDetail>(
                deepLinks = listOf(navDeepLink<ScreenRouteDef.CategoryDetail>( basePath = DeepLink.CATEGORY_DETAIL))
            ) { backStackEntry ->
                val categoryId = backStackEntry.toRoute<ScreenRouteDef.CategoryDetail>().categoryId

                CategoryDetailScreen(modifier = modifier, categoryId = categoryId) { productId ->
                    navController.navigate(ScreenRouteDef.ProductDetail(productId))
                }
            }
        }

        navigation<ScreenRouteDef.MyPageGraph>(
            startDestination = ScreenRouteDef.MyPage
        ) {
            composable<ScreenRouteDef.MyPage> {
                MyPageScreen(modifier)
            }
        }

        navigation<ScreenRouteDef.LikeGraph>(
            startDestination = ScreenRouteDef.Like
        ) {
            composable<ScreenRouteDef.Like> {
                LikeScreen(modifier) { productId ->
                    navController.navigate(ScreenRouteDef.ProductDetail(productId))
                }
            }
        }

        composable<ScreenRouteDef.ProductDetail>(
            deepLinks = listOf(navDeepLink<ScreenRouteDef.ProductDetail>( basePath = DeepLink.PRODUCT_DETAIL))
        ) { backStackEntry ->
            val productId = backStackEntry.toRoute<ScreenRouteDef.ProductDetail>().productId

            ProductDetailScreen(productId)
        }

        composable<ScreenRouteDef.Search> {
            SearchScreen(modifier) { productId ->
                navController.navigate(ScreenRouteDef.ProductDetail(productId))
            }
        }

        composable<ScreenRouteDef.Basket> {
            BasketScreen(modifier)
        }
    }
}