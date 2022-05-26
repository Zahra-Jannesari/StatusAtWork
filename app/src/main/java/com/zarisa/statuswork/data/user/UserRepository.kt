package com.zarisa.statuswork.data.user

import com.zarisa.statuswork.model.User

class UserRepository(
    var userLocalDataSource: UserLocalDataSource,
    var userRemoteDataSource: UserRemoteDataSource
) {
    lateinit var currentUser:User
    suspend fun registerUser(user: User):User{
        currentUser=userRemoteDataSource.registerUser(user)
        return currentUser
    }
}