package com.kamil.challengechapter4.model.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "stock_table")
data class StocksEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    @ColumnInfo(name = "code_stock")
    var codeStock: String?,
    @ColumnInfo(name = "name_stock")
    var nameStock: String?,
    @ColumnInfo(name = "value_equity")
    var valueEquity: Int?,
    @ColumnInfo(name = "value_net_profit")
    var valueNetProfit: Int?,
    @ColumnInfo(name = "pbv")
    var priceBookValue: Double?,
    @ColumnInfo(name = "eps")
    var earningsPerShare: Double?,
    @ColumnInfo(name = "share_price")
    var sharePrice: Int?,
    @ColumnInfo(name = "share_stock")
    var shareStock: Int?,
    @ColumnInfo(name = "user_stock")
    var userStock: String?
) : Parcelable
