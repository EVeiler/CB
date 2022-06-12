package com.kamil.challengechapter4.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kamil.challengechapter4.model.database.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRegisterUser(user: UserEntity)

    @Query("SELECT * FROM user_table WHERE username LIKE :userName")
    suspend fun getUsername(userName: String): UserEntity?

    /*@Query("SELECT * FROM user_table")
    fun checkUser(): MutableList<UserEntity>

    @Query("SELECT EXISTS(SELECT username FROM user_table WHERE username = :username)")
    fun existsRegisterUser(username: String): Boolean

    @Query("SELECT EXISTS(SELECT * FROM user_table WHERE username = :username and password = :password)")
    fun loginUser(username: String, password: String) : Boolean*/
}