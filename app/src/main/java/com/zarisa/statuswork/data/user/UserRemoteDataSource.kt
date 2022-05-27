package com.zarisa.statuswork.data.user

import com.zarisa.statuswork.data.network.LoginService
import com.zarisa.statuswork.model.User

class UserRemoteDataSource(var loginApiService: LoginService) {

    suspend fun registerUser(user: User): User {
        return loginApiService.registerUser(user)
    }

    suspend fun getUser(id: String): User {
        return loginApiService.getUser(id)
    }

    suspend fun updateUser(id: String, user: User) {
        return loginApiService.updateUser(user, id)
    }
}