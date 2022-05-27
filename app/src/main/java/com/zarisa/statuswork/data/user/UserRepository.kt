package com.zarisa.statuswork.data.user

import com.zarisa.statuswork.model.User

class UserRepository(
    var userLocalDataSource: UserLocalDataSource,
    var userRemoteDataSource: UserRemoteDataSource
) {
    lateinit var currentUser: User
    suspend fun registerUser(user: User): User {
        currentUser = userRemoteDataSource.registerUser(user)
        return currentUser
    }

    suspend fun getUser(id: String, password: Int): Boolean {
        var loginSuccessful: Boolean = false
        userRemoteDataSource.getUser(id).let {
            if (it.password == password) {
                currentUser = it
                loginSuccessful = true
            }
        }
        return loginSuccessful
    }
    suspend fun updateStatus(status:String){
        currentUser.status=status
        userRemoteDataSource.updateUser(currentUser.id,currentUser)
        currentUser=userRemoteDataSource.getUser(currentUser.id)
    }
}