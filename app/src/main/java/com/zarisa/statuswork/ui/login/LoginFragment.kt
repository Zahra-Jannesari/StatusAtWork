package com.zarisa.statuswork.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.zarisa.statuswork.R
import com.zarisa.statuswork.databinding.FragmentLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {
    val vModel: LoginViewModel by viewModel()
    lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClicks()
        observers()
    }

    private fun observers() {
        vModel.loginSuccessful.observe(viewLifecycleOwner) {
            if (it.isNotBlank()) Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            if (it == "Login successful.") findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }
    }

    private fun onClicks() {
        binding.btnLogin.setOnClickListener {
            if (binding.editTextId.text.isNullOrBlank() || binding.editTextPassword.text.isNullOrBlank())
                Toast.makeText(requireContext(), "Fill in all information.", Toast.LENGTH_SHORT)
                    .show()
            else
                vModel.login(
                    binding.editTextId.text.toString(),
                    Integer.parseInt(binding.editTextPassword.text.toString())
                )
        }
    }

}