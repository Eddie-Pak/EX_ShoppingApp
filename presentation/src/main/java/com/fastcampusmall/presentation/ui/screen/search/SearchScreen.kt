package com.fastcampusmall.presentation.ui.screen.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fastcampusmall.domain.model.SearchFilter
import com.fastcampusmall.presentation.viewmodel.SearchViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    onProductClick: (String) -> Unit,
) {
    val searchFilter by viewModel.searchFilters.collectAsStateWithLifecycle()

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            skipHiddenState = false
        )
    )
    val scope = rememberCoroutineScope()
    var currentFilterType by remember { mutableStateOf<SearchFilter.Type?>(null) }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetDragHandle = { BottomSheetDefaults.DragHandle() },
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
            ) {
                when (currentFilterType) {
                    SearchFilter.Type.CATEGORY -> {
                        val categoryFilter =
                            searchFilter.find { it is SearchFilter.CategoryFilter } as? SearchFilter.CategoryFilter
                        categoryFilter?.let {
                            SearchFilterCategoryContent(filter = it) { updatedFilter ->
                                scope.launch { scaffoldState.bottomSheetState.hide() }
                                viewModel.updateFilter(updatedFilter)
                            }
                        }
                    }

                    SearchFilter.Type.PRICE -> {
                        val priceFilter =
                            searchFilter.find { it is SearchFilter.PriceFilter } as? SearchFilter.PriceFilter
                        priceFilter?.let {
                            SearchFilterPriceContent(filter = it) { updatedFilter ->
                                scope.launch { scaffoldState.bottomSheetState.hide() }
                                viewModel.updateFilter(updatedFilter)
                            }
                        }
                    }

                    null -> {

                    }
                }
            }
        }
    ) {
        SearchContent(
            viewModel = viewModel,
            openFilterDialog = { type ->
                currentFilterType = type
                scope.launch {
                    scaffoldState.bottomSheetState.expand()
                }
            },
            onProductClick = onProductClick
        )
    }
}