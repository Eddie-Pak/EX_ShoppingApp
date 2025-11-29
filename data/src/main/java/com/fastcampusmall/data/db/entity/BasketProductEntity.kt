package com.fastcampusmall.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.fastcampusmall.data.db.converter.BasketProductConverter
import com.fastcampusmall.domain.model.Category
import com.fastcampusmall.domain.model.Price
import com.fastcampusmall.domain.model.Product
import com.fastcampusmall.domain.model.Shop

@Entity(tableName = "basket_product")
@TypeConverters(BasketProductConverter::class)
data class BasketProductEntity(
    @PrimaryKey
    val productId: String,
    val productName: String,
    val imageUrl: String,
    val price: Price,
    val category: Category,
    val shop: Shop,
    val isNew: Boolean,
    val isFreeShipping: Boolean
)

fun BasketProductEntity.toDomainModel() : Product {
    return Product(
        productId = productId,
        productName = productName,
        imageUrl = imageUrl,
        price = price,
        category = category,
        shop = shop,
        isNew = isNew,
        isFreeShipping = isFreeShipping
    )
}
