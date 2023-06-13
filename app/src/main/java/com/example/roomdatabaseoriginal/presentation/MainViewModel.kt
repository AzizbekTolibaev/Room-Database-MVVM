package com.example.roomdatabaseoriginal.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.roomdatabaseoriginal.data.entity.User
import com.example.roomdatabaseoriginal.domain.usacase.impl.*

class MainViewModel: ViewModel() {

    private val _getAllUsersLiveData = MutableLiveData<MutableList<User>>()
    val getAllUserLiveData: MutableLiveData<MutableList<User>> get() = _getAllUsersLiveData

    suspend fun getAllUsers() {
        _getAllUsersLiveData.value = GetAllUsersImpl().getAllUsers()
    }

    suspend fun addUser(user: User) {
        AddUserImpl().addUser(user)
    }

    suspend fun updateUser(user: User) {
        UpdateUserImpl().updateUser(user)
    }

    suspend fun searchUserByName(name: String) {
        _getAllUsersLiveData.value = SearchUserByNameImpl().searchUserByName(name)
    }

    suspend fun deleteUser(user: User) {
        DeleteUserImpl().deleteUser(user)
    }
}
