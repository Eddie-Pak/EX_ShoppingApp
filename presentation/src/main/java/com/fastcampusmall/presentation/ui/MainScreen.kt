package com.fastcampusmall.presentation.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.fastcampusmall.presentation.navigation.AppNavHost
import com.fastcampusmall.presentation.common.CustomNavigationBar

@Composable
fun MainScreen() {
    val snackBarHostState = remember { SnackbarHostState() }
    val navController = rememberNavController()

    Scaffold(
        snackbarHost = { snackBarHostState },
        bottomBar = { CustomNavigationBar(navController) }
    ) { innerPadding ->
        AppNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}