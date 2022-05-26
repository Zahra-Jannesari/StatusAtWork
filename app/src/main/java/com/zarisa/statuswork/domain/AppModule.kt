package com.zarisa.statuswork.domain

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.zarisa.statuswork.data.network.BASE_URL
import com.zarisa.statuswork.data.user.UserLocalDataSource
import com.zarisa.statuswork.data.user.UserRemoteDataSource
import com.zarisa.statuswork.data.user.UserRepository
import com.zarisa.statuswork.data.network.LoginService
import com.zarisa.statuswork.data.network.client
import com.zarisa.statuswork.ui.home.HomeViewModel
import com.zarisa.statuswork.ui.login.LoginViewModel
import com.zarisa.statuswork.ui.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {
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
    single {
        val retrofit = get() as Retrofit
        val loginApiService = retrofit.create(LoginService::class.java)

        loginApiService
    }
    single {
        UserLocalDataSource()
    }
    single {
        UserRemoteDataSource(get())
    }
    single {
        UserRepository(get(), get())
    }
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { HomeViewModel(get()) }
}