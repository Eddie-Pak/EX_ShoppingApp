package com.fastcampusmall.presentation.ui.screen.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fastcampusmall.domain.model.SearchFilter
import com.fastcampusmall.presentation.ui.component.ProductCard
import com.fastcampusmall.presentation.ui.component.SearchBox
import com.fastcampusmall.presentation.viewmodel.SearchViewModel

@Composable
fun SearchContent(
    viewModel: SearchViewModel,
    openFilterDialog: (SearchFilter.Type) -> Unit,
    onProductClick: (String) -> Unit
) {
    val searchResult by viewModel.searchResult.collectAsStateWithLifecycle()
    val searchFilters by viewModel.searchFilters.collectAsStateWithLifecycle()
    val searchKeywords by viewModel.searchKeywords.collectAsStateWithLifecycle(listOf())
    var keyword by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column {
        SearchBox(keyword = keyword,
            onValueChange = { keyword = it },
            searchAction = {
                viewModel.search(keyword)
                keyboardController?.hide()
            })

        if(searchResult.isEmpty()) {
            Text(
                modifier = Modifier.padding(6.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                text = "최근 검색어"
            )

            LazyColumn(modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(10.dp)
            ) {
                items(searchKeywords.size) { index ->
                    val currentKeyword = searchKeywords.reversed()[index].keyword
                    Button(
                        onClick = {
                            keyword = currentKeyword
                            viewModel.search(keyword)
                        },
                        colors = ButtonDefaults.buttonColors(Color.Unspecified)
                    ) {
                        Text(
                            fontSize = 18.sp,
                            text = currentKeyword
                        )
                    }
                }
            }
        } else {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Button(onClick = { openFilterDialog(SearchFilter.Type.CATEGORY)}) {
                    val filter = searchFilters.find { it.type == SearchFilter.Type.CATEGORY} as? SearchFilter.CategoryFilter

                    if(filter?.selectedCategory == null) {
                        Text("Category")
                    } else {
                        Text ("${filter.selectedCategory?.categoryName}")
                    }
                }

                Spacer(modifier = Modifier.width(20.dp))

                Button(onClick = { openFilterDialog(SearchFilter.Type.PRICE)}) {
                    val filter = searchFilters.find { it.type == SearchFilter.Type.PRICE} as? SearchFilter.PriceFilter

                    if(filter?.selectedRange == null) {
                        Text("Price")
                    } else {
                        Text ("${filter.selectedRange?.first} ~ ${filter.selectedRange?.second}")
                    }
                }
            }

            LazyColumn(modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(10.dp)
            ) {
                items(searchResult.size) { index ->
                    ProductCard(searchResult[index]) { model ->
                        onProductClick(model.productId)
                    }
                }
            }
        }
    }
}