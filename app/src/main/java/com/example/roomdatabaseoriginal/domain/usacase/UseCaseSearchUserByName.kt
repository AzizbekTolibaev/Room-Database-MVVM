package com.example.roomdatabaseoriginal.domain.usacase

import com.example.roomdatabaseoriginal.data.entity.User

interface UseCaseSearchUserByName {

    suspend fun searchUserByName(name: String): MutableList<User>

}
