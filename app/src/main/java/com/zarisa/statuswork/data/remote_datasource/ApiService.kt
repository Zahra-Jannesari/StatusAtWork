package com.zarisa.statuswork.data.remote_datasource

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

val client = OkHttpClient.Builder()
    .addInterceptor(logger)
    .build()
const val BASE_URL = ""


