package com.fastcampusmall.presentation.ui.screen.like

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fastcampusmall.presentation.ui.component.ProductCard
import com.fastcampusmall.presentation.ui.theme.FastcampusmallTheme
import com.fastcampusmall.presentation.viewmodel.LikeViewModel

@Composable
fun LikeScreen(
    modifier: Modifier,
    viewModel: LikeViewModel = hiltViewModel(),
    onClick: (String) -> Unit,
) {
    val likedProducts by viewModel.likedProducts.collectAsStateWithLifecycle(listOf())

    FastcampusmallTheme {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(10.dp)
        ) {
            items(likedProducts.size) { index ->
                ProductCard(
                    product = likedProducts[index],
                    onLikeClick = { product ->
                        viewModel.likeProduct(product)
                    }
                ) { product ->
                    onClick(product.productId)
                }
            }
        }
    }
}