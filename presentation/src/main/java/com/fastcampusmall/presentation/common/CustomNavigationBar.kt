package com.fastcampusmall.presentation.common

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.fastcampusmall.presentation.R
import com.fastcampusmall.presentation.navigation.ScreenRouteDef

@Composable
fun CustomNavigationBar(
    navController: NavHostController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination

    val navigationBarItemList = listOf(
        BottomNaviItem(
            tabName = "Home",
            icon = ImageVector.vectorResource(R.drawable.icon_home),
            route = ScreenRouteDef.Main
        ),
        BottomNaviItem(
            tabName = "Category",
            icon = ImageVector.vectorResource(R.drawable.icon_category),
            route = ScreenRouteDef.Category
        ),
        BottomNaviItem(
            tabName = "MyPage",
            icon = ImageVector.vectorResource(R.drawable.icon_mypage),
            route = ScreenRouteDef.MyPage
        )
    )

    NavigationBar(
        containerColor = Color.White
    ) {
        navigationBarItemList.forEach { item ->
            NavigationBarItem(
                selected = currentRoute?.route == item.route::class.qualifiedName,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }

                        launchSingleTop = true

                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.tabName
                    )
                },
                label = {
                    Text(text = item.tabName)
                }
            )
        }
    }
}