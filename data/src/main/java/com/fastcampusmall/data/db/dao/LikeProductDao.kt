package com.fastcampusmall.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fastcampusmall.data.db.entity.LikeProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LikeProductDao {
    @Query("SELECT * FROM like_product")
    fun getAll(): Flow<List<LikeProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: LikeProductEntity)

    @Query("DELETE FROM like_product WHERE productId = :id")
    suspend fun delete(id: String)

    @Query("DELETE FROM like_product")
    suspend fun deleteAll()
}