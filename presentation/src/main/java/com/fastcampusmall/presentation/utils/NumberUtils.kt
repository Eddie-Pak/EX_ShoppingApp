package com.fastcampusmall.presentation.utils

import com.fastcampusmall.domain.model.BasketProduct
import java.text.NumberFormat

object NumberUtils {
    fun numberFormatPrice(price: Int?): String {
        return NumberFormat.getNumberInstance().format(price ?: 0)
    }

    fun numberFormatForBasketPrice(list: List<BasketProduct>): String {
        val totalPrice = list.sumOf { it.product.price.finalPrice * it.count }
        return numberFormatPrice(totalPrice)
    }
}