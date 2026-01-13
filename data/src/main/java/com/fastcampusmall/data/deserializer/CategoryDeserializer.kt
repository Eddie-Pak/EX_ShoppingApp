package com.fastcampusmall.data.deserializer

import com.fastcampusmall.domain.model.Category
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class CategoryDeserializer : JsonDeserializer<Category> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Category {
        val root = json.asJsonObject
        val id = root.get("categoryId").asString
        val name = root.get("categoryName").asString

        return when (id) {
            "1" -> Category.Top
            "2" -> Category.OuterWear
            "3" -> Category.Dress
            "4" -> Category.Pants
            "5" -> Category.Skirt
            "6" -> Category.Shoes
            "7" -> Category.Bag
            "8" -> Category.FashionAccessories
            else -> Category.Custom(id, name)
        }
    }
}