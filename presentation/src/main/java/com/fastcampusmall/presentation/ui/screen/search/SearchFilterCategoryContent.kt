package com.fastcampusmall.presentation.ui.screen.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fastcampusmall.domain.model.SearchFilter
import com.fastcampusmall.presentation.ui.theme.Purple200
import com.fastcampusmall.presentation.ui.theme.Purple40

@Composable
fun SearchFilterCategoryContent(
    filter: SearchFilter.CategoryFilter,
    onCompleteFilter: (SearchFilter) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        Text(
            text = "카테고리 필터",
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            modifier = Modifier.padding(10.dp)
        )

        LazyColumn(modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(10.dp)
        ) {
            items(filter.categories.size) { index ->
                val category = filter.categories[index]
                Button(
                    onClick = {
                        filter.selectedCategory = category
                        onCompleteFilter(filter)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = if(filter.selectedCategory == category) Purple200
                    else Purple40))
                {
                    Text(
                        fontSize = 18.sp,
                        text = category.categoryName
                    )
                }

            }
        }
    }
}