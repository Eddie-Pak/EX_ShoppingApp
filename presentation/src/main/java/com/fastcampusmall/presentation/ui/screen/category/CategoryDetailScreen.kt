package com.fastcampusmall.presentation.ui.screen.category

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fastcampusmall.domain.model.Category
import com.fastcampusmall.presentation.ui.component.ProductCard
import com.fastcampusmall.presentation.ui.theme.FastcampusmallTheme
import com.fastcampusmall.presentation.viewmodel.CategoryViewModel

@Composable
fun CategoryDetailScreen(
    modifier: Modifier,
    category: Category,
    viewModel: CategoryViewModel = hiltViewModel()
) {
    val products by viewModel.products.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getProductsByCategory(category)
    }

    FastcampusmallTheme {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(10.dp)
        ) {
            items(products.size) { index ->
                ProductCard(product = products[index]) {

                }
            }
        }
    }
}