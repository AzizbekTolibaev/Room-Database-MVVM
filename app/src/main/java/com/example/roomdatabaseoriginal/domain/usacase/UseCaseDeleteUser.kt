package com.example.roomdatabaseoriginal.domain.usacase

import com.example.roomdatabaseoriginal.data.entity.User

interface UseCaseDeleteUser {

    suspend fun deleteUser(user: User)

}
