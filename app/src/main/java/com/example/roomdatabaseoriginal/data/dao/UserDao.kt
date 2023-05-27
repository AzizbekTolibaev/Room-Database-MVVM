package com.example.roomdatabaseoriginal.data.dao

import androidx.room.*
import com.example.roomdatabaseoriginal.data.entity.User

@Dao
interface UserDao {

    @Query("SELECT * FROM table_user")
    suspend fun getAllUsers(): MutableList<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: User)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM table_user WHERE name LIKE '%' || :name || '%'")
    suspend fun searchUserByName(name: String): MutableList<User>
}