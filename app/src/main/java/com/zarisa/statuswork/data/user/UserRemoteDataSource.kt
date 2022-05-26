package com.zarisa.statuswork.data.user

import com.zarisa.statuswork.data.network.LoginService
import com.zarisa.statuswork.model.ApiState
import com.zarisa.statuswork.model.Resource
import com.zarisa.statuswork.model.User

class UserRemoteDataSource(var loginApiService: LoginService) {

    suspend fun registerUser(user: User): User {
        return loginApiService.registerUser(user)
    }
//
//    suspend fun getUser(id: String): Resource<User> {
//
//
//        var result = Resource<User>(ApiState.LOADING , null)
//        try {
//            var response = loginApiService.getUser(id)
//            if(response.isSuccessful){
//                result.data = response.body() as User
//                result.status = ApiState.DONE
//            }else
//                return Resource(ApiState.ERROR , null , response.message())
//        }
//        catch (ex: Exception){
//            return Resource(ApiState.ERROR , null , ex.message)
//        }
//        return result
//    }


    suspend fun getUser(id: String): User {
        return loginApiService.getUser(id)
    }
}