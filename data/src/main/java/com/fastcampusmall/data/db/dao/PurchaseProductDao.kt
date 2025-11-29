package com.fastcampusmall.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fastcampusmall.data.db.entity.PurchaseProductEntity

@Dao
interface PurchaseProductDao {
    @Query("SELECT * FROM purchase_product")
    suspend fun getAll(): List<PurchaseProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: PurchaseProductEntity)

    @Query("DELETE FROM purchase_product WHERE productId = :id")
    suspend fun delete(id: String)
}