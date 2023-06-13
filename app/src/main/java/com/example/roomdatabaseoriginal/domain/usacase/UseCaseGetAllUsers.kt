package com.example.roomdatabaseoriginal.domain.usacase

import com.example.roomdatabaseoriginal.data.entity.User

interface UseCaseGetAllUsers {

    suspend fun getAllUsers(): MutableList<User>

}
