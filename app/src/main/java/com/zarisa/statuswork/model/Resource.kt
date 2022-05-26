package com.zarisa.statuswork.model

data class Resource<T>(var status: ApiState, var data: T?, var message: String? = null)