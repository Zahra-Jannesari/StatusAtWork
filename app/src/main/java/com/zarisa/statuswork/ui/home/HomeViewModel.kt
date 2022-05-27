package com.zarisa.statuswork.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zarisa.statuswork.data.user.UserRepository
import com.zarisa.statuswork.model.ApiState
import com.zarisa.statuswork.model.User
import kotlinx.coroutines.launch

class HomeViewModel(var userRepository: UserRepository) : ViewModel() {
    val connectionState = MutableLiveData<ApiState>()
    fun getUserInfo(): LiveData<User> {
        return MutableLiveData(userRepository.currentUser)
    }

    fun updateStatus(status: String) {
        viewModelScope.launch {
            try {
                connectionState.value = ApiState.LOADING
                userRepository.updateStatus(status)
                connectionState.value = ApiState.DONE
            } catch (e: java.net.UnknownHostException) {
                connectionState.value = ApiState.BAD_CONNECTION
            } catch (e: Exception) {
                connectionState.value = ApiState.ERROR
            }

        }
    }
}