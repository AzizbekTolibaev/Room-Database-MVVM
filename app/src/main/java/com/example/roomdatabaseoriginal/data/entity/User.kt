package com.example.roomdatabaseoriginal.data.entity

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_user")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val surname: String,
    val phoneNumber: Int,
    @DrawableRes val image: Int
)
