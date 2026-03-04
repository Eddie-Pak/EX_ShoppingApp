package com.fastcampusmall.presentation.common

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.fastcampusmall.presentation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(
    title: String,
    onSearchClick: () -> Unit,
    onBasketClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = { Text(text = title) },
        actions = {
            IconButton(
                onClick = {
                    onSearchClick()
                }
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.icon_search),
                    contentDescription = "Search"
                )
            }

            IconButton(
                onClick = {
                    onBasketClick()
                }
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.icon_cart),
                    contentDescription = "Basket"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    )
}