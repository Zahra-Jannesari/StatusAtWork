package com.zarisa.statuswork.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zarisa.statuswork.R
import com.zarisa.statuswork.databinding.FragmentHomeBinding
import com.zarisa.statuswork.model.ApiState
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    val vModel: HomeViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observers()
        onClicks()
    }

    private fun onClicks() {
        binding.btnUpdateStatus.setOnClickListener {
            vModel.updateStatus(binding.editTextStatus.text.toString())
        }
    }

    private fun observers() {
        vModel.getUserInfo().observe(viewLifecycleOwner) {
            //set avatar
            Glide.with(requireActivity())
                .load(it.avatarUrl)
                .placeholder(R.drawable.ic_baseline_person_24)
                .error(R.drawable.ic_baseline_image_not_supported_24)
                .apply(RequestOptions().circleCrop())
                .into(binding.imageViewAvatar)
            //set name
            binding.tvName.text = it.name
            //set id
            binding.tvId.text = "ID: ${it.id}"
            //set status
            binding.editTextStatus.setText(it.status)
        }
        vModel.connectionState.observe(viewLifecycleOwner) {
            when (it) {
                ApiState.DONE -> {
                    Toast.makeText(
                        requireContext(),
                        "Status successfully updated.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                ApiState.LOADING -> {

                }
                ApiState.ERROR -> {
                    Toast.makeText(
                        requireContext(),
                        "Status did NOT update.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    Toast.makeText(
                        requireContext(),
                        "Please check your connection and try again.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

}