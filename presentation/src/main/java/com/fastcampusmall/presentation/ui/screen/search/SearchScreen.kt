package com.fastcampusmall.presentation.ui.screen.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fastcampusmall.presentation.ui.component.ProductCard
import com.fastcampusmall.presentation.ui.component.SearchBox
import com.fastcampusmall.presentation.viewmodel.SearchViewModel

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    onProductClick: (String) -> Unit
) {
    val searchResult by viewModel.searchResult.collectAsStateWithLifecycle()
    val searchKeywords by viewModel.searchKeywords.collectAsStateWithLifecycle(listOf())
    var keyword by remember { mutableStateOf("") }

    Column {
        SearchBox(keyword = keyword,
            onValueChange = { keyword = it },
            searchAction = {
                viewModel.search(keyword)
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