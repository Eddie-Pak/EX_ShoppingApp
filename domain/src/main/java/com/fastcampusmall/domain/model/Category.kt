package com.fastcampusmall.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
sealed class Category(
    open val categoryId: String,
    open val categoryName: String
) : Parcelable {

    @Serializable
    @Parcelize
    data object Top : Category("1", "상의")

    @Serializable
    @Parcelize
    data object OuterWear : Category("2", "아우터")

    @Serializable
    @Parcelize
    data object Dress : Category("3", "드레스")

    @Serializable
    @Parcelize
    data object Pants : Category("4", "바지")

    @Serializable
    @Parcelize
    data object Skirt : Category("5", "치마")

    @Serializable
    @Parcelize
    data object Shoes : Category("6", "신발")

    @Serializable
    @Parcelize
    data object Bag : Category("7", "가방")

    @Serializable
    @Parcelize
    data object FashionAccessories : Category("8", "패션잡화")

    data class Custom(
        override val categoryId: String,
        override val categoryName: String
    ) : Category(categoryId, categoryName)
}
