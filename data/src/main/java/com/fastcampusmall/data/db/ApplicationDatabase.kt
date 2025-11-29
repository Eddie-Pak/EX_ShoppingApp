package com.fastcampusmall.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fastcampusmall.data.db.dao.BasketProductDao
import com.fastcampusmall.data.db.dao.LikeProductDao
import com.fastcampusmall.data.db.dao.PurchaseProductDao
import com.fastcampusmall.data.db.entity.BasketProductEntity
import com.fastcampusmall.data.db.entity.LikeProductEntity
import com.fastcampusmall.data.db.entity.PurchaseProductEntity

@Database(
    entities = [
        BasketProductEntity::class,
        LikeProductEntity::class,
        PurchaseProductEntity::class
    ],
    version = 1
)
abstract class ApplicationDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "ApplicationDatabase.db"
    }

    abstract fun basketProductDao(): BasketProductDao

    abstract fun likeProductDao(): LikeProductDao

    abstract fun purchaseProductDao(): PurchaseProductDao
}