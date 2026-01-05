package com.fastcampusmall.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fastcampusmall.domain.model.Product
import com.fastcampusmall.presentation.R

@Composable
fun RankingProductCard(product: Product, index: Int, onClick: (Product) -> Unit) {
    Row(
        modifier = Modifier.padding(10.dp)
            .fillMaxWidth()
            .clickable { onClick(product) }
    ) {
        Text(
            modifier = Modifier.padding(0.dp, 0.dp, 10.dp, 0.dp),
            text = "${index + 1}",
            fontWeight = FontWeight.Bold
        )

        Image(
            modifier = Modifier.width(80.dp)
                .aspectRatio(0.7f),
            painter = painterResource(R.drawable.product_image),
            contentDescription = "description",
            contentScale = ContentScale.Crop,
        )

        Column(
            modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp)
        ) {
            Text(
                modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp),
                text = product.shop.shopName,
                fontSize = 14.sp
            )

            Text(
                modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp),
                text = product.productName,
                fontSize = 14.sp
            )

            PriceText(product)
        }
    }
}