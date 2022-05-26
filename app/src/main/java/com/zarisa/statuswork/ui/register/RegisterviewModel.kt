package com.zarisa.statuswork.ui.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zarisa.statuswork.data.user.UserRepository
import com.zarisa.statuswork.model.ApiState
import com.zarisa.statuswork.model.User
import kotlinx.coroutines.launch

class RegisterViewModel(var userRepository: UserRepository) : ViewModel() {
    val userId = MutableLiveData<String>()
    val state = MutableLiveData<ApiState>()
    fun registerUser(user: User) {
        viewModelScope.launch {
            try {
                state.value = ApiState.LOADING
                userId.value = userRepository.registerUser(user).id
                state.value = ApiState.DONE
            }catch (e:java.net.UnknownHostException){
                state.value = ApiState.BAD_CONNECTION
            }
            catch (e:Exception){
                state.value = ApiState.ERROR
            }

        }
    }
}