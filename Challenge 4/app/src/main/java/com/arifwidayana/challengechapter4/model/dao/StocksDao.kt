package com.kamil.challengechapter4.model.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.kamil.challengechapter4.model.database.StocksEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StocksDao {
    @Query("SELECT * FROM stock_table WHERE user_stock = :user")
    fun getDataStocks(user: String): List<StocksEntity>

    @Insert(onConflict = REPLACE)
    suspend fun insertDataStocks(stocks: StocksEntity): Long

    @Update
    suspend fun updateDataStocks(stocks: StocksEntity): Int

    @Delete
    suspend fun deleteDataStocks(stocks: StocksEntity): Int
}