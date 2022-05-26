package com.zarisa.statuswork.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zarisa.statuswork.data.user.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(var userRepository: UserRepository) : ViewModel() {

    val loginSuccessful = MutableLiveData<String>()
    fun login(id: String, password: Int) {
        viewModelScope.launch {
            try {
                userRepository.getUser(id, password).let {
                    loginSuccessful.value =
                        if (it) "Login successful." else "Password does not match!"
                }

            } catch (e: retrofit2.HttpException) {
                loginSuccessful.value = "Please check your id and try again."
            }
            catch (e:java.net.UnknownHostException){
                loginSuccessful.value = "Please check your connection and try again."
            }
            catch (e:Exception){
                loginSuccessful.value = "Something went wrong. Try again."
            }
        }
    }
}