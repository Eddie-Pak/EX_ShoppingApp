package com.fastcampusmall.presentation.ui.screen.category

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fastcampusmall.domain.model.Category
import com.fastcampusmall.presentation.ui.theme.FastcampusmallTheme
import com.fastcampusmall.presentation.viewmodel.CategoryViewModel

@Composable
fun CategoryMainScreen(
    modifier: Modifier,
    viewModel: CategoryViewModel = hiltViewModel(),
    onCategoryClick: (Category) -> Unit,
) {
    val categories by viewModel.categories.collectAsState(listOf())

    FastcampusmallTheme {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = modifier.fillMaxSize()
        ) {
            items(count = categories.size, span = { GridItemSpan(1) }) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(10.dp)
                        .shadow(10.dp),
                    onClick = { onCategoryClick(categories[it]) },
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = categories[it].categoryName,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}