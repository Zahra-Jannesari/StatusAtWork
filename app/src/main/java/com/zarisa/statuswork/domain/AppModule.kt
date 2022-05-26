package com.zarisa.statuswork.domain

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.zarisa.statuswork.data.user.BASE_URL
import com.zarisa.statuswork.data.user.UserLocalDataSource
import com.zarisa.statuswork.data.user.UserRepository
import com.zarisa.statuswork.data.user.client
import com.zarisa.statuswork.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {
    single {
        UserRepository(get(),get())
    }
    single {
        UserLocalDataSource()
    }
    single {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .client(client)
            .build()
        retrofit
    }
    viewModel{LoginViewModel(get())}
}