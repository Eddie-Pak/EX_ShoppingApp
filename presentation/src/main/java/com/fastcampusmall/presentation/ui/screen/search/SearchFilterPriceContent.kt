package com.fastcampusmall.presentation.ui.screen.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fastcampusmall.domain.model.SearchFilter
import com.fastcampusmall.presentation.ui.theme.Purple200

@Composable
fun SearchFilterPriceContent(
    filter: SearchFilter.PriceFilter,
    onCompleteFilter: (SearchFilter) -> Unit,
) {
    var sliderValues by remember {
        val selectedRange = filter.selectedRange
        if(selectedRange == null) {
            mutableStateOf(filter.priceRange.first..filter.priceRange.second)
        } else {
            mutableStateOf(selectedRange.first..selectedRange.second)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(20.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "가격 필터",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                modifier = Modifier.padding(10.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    filter.selectedRange = sliderValues.start to sliderValues.endInclusive
                    onCompleteFilter(filter)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Purple200))
            {
                Text(
                    fontSize = 18.sp,
                    text = "완료"
                )
            }
        }

        RangeSlider(
            value = sliderValues,
            onValueChange = {
                sliderValues = it
            },
            valueRange = filter.priceRange.first..filter.priceRange.second,
            steps = 9,
        )

        Text(text = "최저가: ${sliderValues.start} ~ 최고가 : ${sliderValues.endInclusive}")
    }
}