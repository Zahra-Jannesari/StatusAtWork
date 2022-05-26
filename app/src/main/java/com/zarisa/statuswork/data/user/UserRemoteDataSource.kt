package com.zarisa.statuswork.data.user

import com.zarisa.statuswork.data.network.LoginService
import com.zarisa.statuswork.model.User

class UserRemoteDataSource (var loginApiService: LoginService){

    suspend fun registerUser(user: User):User{
        return loginApiService.registerUser(user)
    }
}