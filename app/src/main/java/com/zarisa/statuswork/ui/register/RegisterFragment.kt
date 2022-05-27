package com.zarisa.statuswork.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.zarisa.statuswork.R
import com.zarisa.statuswork.databinding.FragmentRegisterBinding
import com.zarisa.statuswork.model.ApiState
import com.zarisa.statuswork.model.User
import org.koin.androidx.viewmodel.ext.android.viewModel


class RegisterFragment : Fragment() {

    private val vModel: RegisterViewModel by viewModel()
    lateinit var binding: FragmentRegisterBinding
    var onRegisterClicked = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observers()
        onClicks()
    }

    private fun observers() {
        vModel.state.observe(viewLifecycleOwner) {
            when (it) {
                ApiState.DONE -> {
                    binding.btnRegister.let { btn ->
                        btn.text = "continue"
                        btn.isEnabled = true
                    }
                }
                ApiState.LOADING -> {
                    binding.btnRegister.let { btn ->
                        btn.isEnabled = false
                        btn.text = "loading..."
                    }
                }
                ApiState.ERROR -> {
                    binding.btnRegister.let { btn ->
                        btn.text = "Register"
                        btn.isEnabled = true
                    }
                    Toast.makeText(
                        requireContext(),
                        "Something went wrong. Please try again.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    binding.btnRegister.let { btn ->
                        btn.text = "Register"
                        btn.isEnabled = true
                    }
                    Toast.makeText(
                        requireContext(),
                        "Please check your connection and try again.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        vModel.userId.observe(viewLifecycleOwner) {
            binding.tvUserId.text = "Your id:$it"
            Toast.makeText(requireContext(), "Your id:$it", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onClicks() {
        binding.btnRegister.setOnClickListener {
            if (!onRegisterClicked) {
                if (dataIsValid()) {
                    vModel.registerUser(
                        User(
                            name = binding.editTextName.text.toString(),
                            avatarUrl = binding.editTextAvatarUrl.text.toString(),
                            password = Integer.parseInt(binding.editTextPassword.text.toString())
                        )
                    )
                    onRegisterClicked = true
                } else
                    Toast.makeText(requireContext(), "data is not valid", Toast.LENGTH_SHORT).show()
            } else findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }
        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }

    private fun dataIsValid(): Boolean {
        var dataIsValid = true
        if (binding.editTextName.text.isNullOrBlank() ||
            binding.editTextAvatarUrl.text.isNullOrBlank() ||
            binding.editTextPassword.text.isNullOrBlank()
        )
            dataIsValid = false
        return dataIsValid
    }


}