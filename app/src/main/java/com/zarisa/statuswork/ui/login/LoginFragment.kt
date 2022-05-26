package com.zarisa.statuswork.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zarisa.statuswork.R
import com.zarisa.statuswork.databinding.FragmentLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {
    val vModel: LoginViewModel by viewModel()
    lateinit var binding:FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}