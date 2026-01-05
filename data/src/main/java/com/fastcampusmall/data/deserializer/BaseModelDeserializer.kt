package com.fastcampusmall.data.deserializer

import com.fastcampusmall.domain.model.Banner
import com.fastcampusmall.domain.model.BannerList
import com.fastcampusmall.domain.model.BaseModel
import com.fastcampusmall.domain.model.Carousel
import com.fastcampusmall.domain.model.ModelType
import com.fastcampusmall.domain.model.Product
import com.fastcampusmall.domain.model.Ranking
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class BaseModelDeserializer : JsonDeserializer<BaseModel> {
    companion object {
        private const val TYPE = "type"
    }

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?,
    ): BaseModel {
        val root = json?.asJsonObject
        val gson = GsonBuilder().create()

        val typeString = root?.get(TYPE)?.asString ?: ""

        return when (ModelType.valueOf(typeString)) {
            ModelType.BANNER -> gson.fromJson(root, Banner::class.java)

            ModelType.PRODUCT -> gson.fromJson(root, Product::class.java)

            ModelType.BANNER_LIST -> gson.fromJson(root, BannerList::class.java)

            ModelType.CAROUSEL -> gson.fromJson(root, Carousel::class.java)

            ModelType.RANKING -> gson.fromJson(root, Ranking::class.java)
        }
    }
}