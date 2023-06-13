package com.example.roomdatabaseoriginal.domain.usacase.impl

import com.example.roomdatabaseoriginal.App
import com.example.roomdatabaseoriginal.data.UserDatabase
import com.example.roomdatabaseoriginal.data.entity.User
import com.example.roomdatabaseoriginal.domain.MainRepository
import com.example.roomdatabaseoriginal.domain.usacase.UseCaseUpdateUser

class UpdateUserImpl: UseCaseUpdateUser {
    val repo = MainRepository(UserDatabase.getInstance(App.instance).getUserDao())

    override suspend fun updateUser(user: User) {
        repo.updateUser(user)
    }

}