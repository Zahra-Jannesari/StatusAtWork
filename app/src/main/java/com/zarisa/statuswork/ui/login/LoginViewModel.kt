package com.zarisa.statuswork.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zarisa.statuswork.data.user.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(var userRepository: UserRepository) : ViewModel() {

    val loginSuccessful = MutableLiveData<Boolean>()
    fun login(id: String, password: Int) {
        viewModelScope.launch {
            try {
                loginSuccessful.value = userRepository.getUser(id, password)
            } catch (e: Exception) {
                Log.d("TAG", "login:failed $e ")
            }
        }
    }
}