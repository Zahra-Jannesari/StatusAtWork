package com.zarisa.statuswork.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zarisa.statuswork.R
import com.zarisa.statuswork.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}