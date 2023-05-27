package com.example.roomdatabaseoriginal.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdatabaseoriginal.data.entity.User
import com.example.roomdatabaseoriginal.data.dao.UserDao

@Database(entities = [User::class], version = 1)
abstract class UserDatabase: RoomDatabase() {

    abstract fun getUserDao(): UserDao

    companion object {
        const val DATABASE_NAME = "db_name"

        fun getInstance(context: Context): UserDatabase {
            return Room.databaseBuilder(
                context, UserDatabase::class.java, DATABASE_NAME
            ).fallbackToDestructiveMigration().build()
        }
    }
}