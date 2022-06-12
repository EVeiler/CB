package com.kamil.challengechapter4.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kamil.challengechapter4.model.dao.StocksDao
import com.kamil.challengechapter4.model.dao.UserDao
import com.kamil.challengechapter4.model.database.StocksEntity
import com.kamil.challengechapter4.model.database.UserEntity

@Database (entities = [UserEntity::class, StocksEntity::class], version = 1, exportSchema = false)
abstract class StocksDatabase : RoomDatabase() {
    abstract fun stocksDao(): StocksDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var userDatabase: StocksDatabase? = null

        fun getData(context: Context): StocksDatabase {

            return userDatabase ?: synchronized(this) {
                val user = Room.databaseBuilder(
                    context.applicationContext,
                    StocksDatabase::class.java,
                    "User.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                userDatabase = user
                user
            }
//            if (userDatabase == null) {
//                synchronized(StocksDatabase::class.java){
//                    userDatabase = Room.databaseBuilder(context.applicationContext, StocksDatabase::class.java, "User.db").build()
//                }
//            }
//            return userDatabase
        }

        fun destroyData() {
            userDatabase = null
        }
    }
}

