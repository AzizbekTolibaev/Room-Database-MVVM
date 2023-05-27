package com.example.roomdatabaseoriginal.domain

import com.example.roomdatabaseoriginal.data.dao.UserDao
import com.example.roomdatabaseoriginal.data.entity.User

class MainRepository(private val dao: UserDao) {
    suspend fun getAllUsers() = dao.getAllUsers()

    suspend fun addUser(user: User) = dao.addUser(user)

    suspend fun updateUser(user: User) = dao.updateUser(user)

    suspend fun searchUserByName(name: String) = dao.searchUserByName(name)

    suspend fun deleteUser(user: User) = dao.deleteUser(user)
}