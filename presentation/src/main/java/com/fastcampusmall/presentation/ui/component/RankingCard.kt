package com.fastcampusmall.presentation.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fastcampusmall.domain.model.Product
import com.fastcampusmall.domain.model.Ranking

@Composable
fun RankingCard(model: Ranking, onClick: (Product) -> Unit) {
    val pagerState = rememberPagerState(pageCount = { model.productList.size / DEFAULT_RANKING_ITEM_COUNT })

    Column {
        Text(
            modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp),
            text = model.title,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )

        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(0.dp, 0.dp, 50.dp, 0.dp)
        ) { index ->
            Column {
                RankingProductCard(
                    index = index * 3,
                    product = model.productList[index * 3]
                ) {
                    onClick(it)
                }

                RankingProductCard(
                    index = index * 3 + 1,
                    product = model.productList[index * 3 + 1]
                ) {
                    onClick(it)
                }

                RankingProductCard(
                    index = index * 3 + 2,
                    product = model.productList[index * 3 + 2]
                ) {
                    onClick(it)
                }
            }
        }
    }
}

private const val DEFAULT_RANKING_ITEM_COUNT = 3