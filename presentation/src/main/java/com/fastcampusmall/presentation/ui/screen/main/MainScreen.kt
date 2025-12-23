package com.fastcampusmall.presentation.ui.screen.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.fastcampusmall.domain.model.Banner
import com.fastcampusmall.domain.model.BannerList
import com.fastcampusmall.domain.model.ModelType
import com.fastcampusmall.domain.model.Product
import com.fastcampusmall.presentation.ui.component.BannerCard
import com.fastcampusmall.presentation.ui.component.BannerListCard
import com.fastcampusmall.presentation.ui.component.ProductCard
import com.fastcampusmall.presentation.ui.theme.FastcampusmallTheme
import com.fastcampusmall.presentation.viewmodel.MainViewModel

@Composable
fun MainScreen(
    modifier: Modifier,
    viewModel: MainViewModel = hiltViewModel(),
) {
    val modelList by viewModel.modelList.collectAsState(listOf())
    val columnCount by viewModel.columnCount.collectAsState()

    FastcampusmallTheme {
        LazyVerticalGrid(
            columns = GridCells.Fixed(columnCount),
            modifier = modifier.fillMaxSize()
        ) {
            items(modelList.size, span = { index ->
                val item = modelList[index]
                val spanCount = getSpanCountByType(item.type, columnCount)

                GridItemSpan(spanCount)
            }) {
                when (val item = modelList[it]) {
                    is Banner -> BannerCard(item)

                    is Product -> ProductCard(item) {

                    }

                    is BannerList -> BannerListCard(item)
                }
            }
        }
    }
}

private fun getSpanCountByType(type: ModelType, defaultColumnCount: Int) : Int {
    return when (type) {
        ModelType.BANNER, ModelType.BANNER_LIST -> defaultColumnCount

        ModelType.PRODUCT -> 1
    }
}