package com.fastcampusmall.data.datasource

import android.content.Context
import com.fastcampusmall.domain.model.BaseModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.InputStreamReader
import javax.inject.Inject

class ProductDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
    private val gson: Gson
) {
    fun getProducts(): Flow<List<BaseModel>> = flow {
        val inputStream = context.assets.open("product_list.json")
        val inputStreamReader = InputStreamReader(inputStream)
        val jsonString = inputStreamReader.readText()
        val type = object : TypeToken<List<BaseModel>>() {}.type
        val list = gson.fromJson<List<BaseModel>>(jsonString, type)

        emit(list)
    }
}