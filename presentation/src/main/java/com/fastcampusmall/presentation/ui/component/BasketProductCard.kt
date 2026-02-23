package com.fastcampusmall.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fastcampusmall.domain.model.BasketProduct
import com.fastcampusmall.domain.model.Product
import com.fastcampusmall.presentation.R

@Composable
fun BasketProductCard(
    basketProduct: BasketProduct,
    onRemoveClick: (Product) -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.product_image),
                "description",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(120.dp)
                    .aspectRatio(1f)
            )
            Column(
                modifier = Modifier
                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
                    .weight(1f)
            ) {
                Text(
                    fontSize = 14.sp,
                    text = basketProduct.product.shop.shopName,
                    modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)
                )

                Text(
                    fontSize = 14.sp,
                    text = basketProduct.product.productName,
                    modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)
                )

                PriceText(product = basketProduct.product)
            }
            Text(
                text = "${basketProduct.count} 개",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(30.dp)
            )
        }

        IconButton(
            onClick = { onRemoveClick(basketProduct.product) },
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.icon_delete_x),
                contentDescription = "delete"
            )
        }
    }
}