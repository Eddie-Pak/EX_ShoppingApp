package com.fastcampusmall.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.fastcampusmall.data.db.converter.LikeProductConverter
import com.fastcampusmall.domain.model.Category
import com.fastcampusmall.domain.model.Price
import com.fastcampusmall.domain.model.Product
import com.fastcampusmall.domain.model.Shop

@Entity(tableName = "like_product")
@TypeConverters(LikeProductConverter::class)
data class LikeProductEntity(
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

fun LikeProductEntity.toDomainModel() : Product {
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