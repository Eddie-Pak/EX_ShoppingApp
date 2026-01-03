package com.fastcampusmall.presentation.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fastcampusmall.domain.model.Carousel
import com.fastcampusmall.domain.model.Product

@Composable
fun CarouselCard(
    model: Carousel,
    onClick: (Product) -> Unit
) {
    val scrollState = rememberLazyListState()
    Column {
        Text(
            text = model.title,
            modifier = Modifier.padding(10.dp),
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp
        )

        LazyRow(
            state = scrollState,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            items(model.productList.size) {
                CarouselProductCard(model.productList[it]) {
                    onClick(it)
                }
            }
        }
    }
}