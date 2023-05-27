package com.example.roomdatabaseoriginal.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.roomdatabaseoriginal.data.UserDatabase
import com.example.roomdatabaseoriginal.data.entity.User
import com.example.roomdatabaseoriginal.domain.MainRepository

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val repo = MainRepository(UserDatabase.getInstance(application).getUserDao())

    private val _getAllUsersLiveData = MutableLiveData<MutableList<User>>()
    val getAllUserLiveData: MutableLiveData<MutableList<User>> get() = _getAllUsersLiveData

    suspend fun getAllUsers() {
        _getAllUsersLiveData.value = repo.getAllUsers()
    }

    suspend fun addUser(user: User) {
        repo.addUser(user)
    }

    suspend fun updateUser(user: User) {
        repo.updateUser(user)
    }

    suspend fun searchUserByName(name: String) {
        _getAllUsersLiveData.value = repo.searchUserByName(name)
    }

    suspend fun deleteUser(user: User) {
        repo.deleteUser(user)
    }
}
