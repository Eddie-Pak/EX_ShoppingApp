package com.fastcampusmall.presentation.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.fastcampusmall.domain.model.Product
import com.fastcampusmall.domain.model.SalesStatus
import com.fastcampusmall.presentation.ui.theme.Purple200

@Composable
fun PriceText(product: Product) {
    when(product.price.salesStatus) {
        SalesStatus.ON_SALE -> {
            Text(
                text = "${product.price.originPrice}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        SalesStatus.ON_DISCOUNT -> {
            Text(
                fontSize = 14.sp,
                text = "${product.price.originPrice}원",
                style = TextStyle(textDecoration = TextDecoration.LineThrough)
            )
            Text(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Purple200,
                text = "할인가: ${product.price.finalPrice}원"
            )
        }
        SalesStatus.SOLD_OUT -> {
            Text(
                fontSize = 18.sp,
                color = Color(0xFF666666),
                text = "판매종료"
            )
        }
    }
}