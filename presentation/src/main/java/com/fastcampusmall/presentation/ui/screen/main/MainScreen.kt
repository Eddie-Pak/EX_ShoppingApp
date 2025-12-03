package com.fastcampusmall.presentation.ui.screen.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.fastcampusmall.presentation.ui.component.ProductCard
import com.fastcampusmall.presentation.ui.theme.FastcampusmallTheme
import com.fastcampusmall.presentation.viewmodel.MainViewModel

@Composable
fun MainScreen(
    modifier: Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    val productList by viewModel.productList.collectAsState(listOf())

    FastcampusmallTheme {
        LazyColumn(
            modifier = modifier.fillMaxSize()
        ) {
            items(productList.size) {
                ProductCard(productList[it]) {

                }
            }
        }
    }
}