package com.fastcampusmall.presentation.ui.screen.category

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fastcampusmall.presentation.ui.component.ProductCard
import com.fastcampusmall.presentation.ui.theme.FastcampusmallTheme
import com.fastcampusmall.presentation.viewmodel.CategoryViewModel

@Composable
fun CategoryDetailScreen(
    modifier: Modifier,
    categoryId: String,
    viewModel: CategoryViewModel = hiltViewModel(),
    onProductClick: (String) -> Unit
) {
    val products by viewModel.products.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getProductsByCategory(categoryId)
    }

    FastcampusmallTheme {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(10.dp)
        ) {
            items(products.size) { index ->
                ProductCard(
                    product = products[index],
                    onLikeClick = { product ->
                        viewModel.likeProduct(product)
                    }
                ) { product ->
                    onProductClick(product.productId)
                }
            }
        }
    }
}