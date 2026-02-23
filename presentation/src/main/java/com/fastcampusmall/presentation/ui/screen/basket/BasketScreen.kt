package com.fastcampusmall.presentation.ui.screen.basket

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fastcampusmall.domain.model.BasketProduct
import com.fastcampusmall.presentation.ui.component.BasketProductCard
import com.fastcampusmall.presentation.ui.theme.FastcampusmallTheme
import com.fastcampusmall.presentation.ui.theme.Purple200
import com.fastcampusmall.presentation.utils.NumberUtils
import com.fastcampusmall.presentation.viewmodel.BasketViewModel

@Composable
fun BasketScreen(
    modifier: Modifier,
    viewModel: BasketViewModel = hiltViewModel()
) {
    val basketProducts by viewModel.basketProducts.collectAsStateWithLifecycle(listOf())

    FastcampusmallTheme {
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentPadding = PaddingValues(10.dp)
            ) {
                items(basketProducts.size) { index ->
                    BasketProductCard(basketProduct = basketProducts[index]) {
                        viewModel.removeBasketProduct(it)
                    }
                }
            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Purple200
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth()
                        .padding(5.dp),
                    fontSize = 16.sp,
                    text ="${NumberUtils.numberFormatForBasketPrice(basketProducts)} 결제하기."
                )
            }
        }
    }
}