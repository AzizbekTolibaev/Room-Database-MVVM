package com.example.roomdatabaseoriginal.domain.usacase

import com.example.roomdatabaseoriginal.data.entity.User

interface UseCaseUpdateUser {

    suspend fun updateUser(user: User)

}
