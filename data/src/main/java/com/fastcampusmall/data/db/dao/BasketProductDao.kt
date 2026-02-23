package com.fastcampusmall.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fastcampusmall.data.db.entity.BasketProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BasketProductDao {
    @Query("SELECT * FROM basket_product")
    fun getAll(): Flow<List<BasketProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: BasketProductEntity)

    @Query("SELECT * FROM basket_product WHERE productId = :id")
    suspend fun get(id: String): BasketProductEntity?

    @Query("DELETE FROM basket_product WHERE productId = :id")
    suspend fun delete(id: String)

    @Query("DELETE FROM basket_product")
    suspend fun deleteAll()
}