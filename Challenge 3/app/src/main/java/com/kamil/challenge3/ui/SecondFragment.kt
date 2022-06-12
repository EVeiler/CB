package com.kamil.challenge3.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.kamil.challenge3.R
import com.kamil.challenge3.data.Identity
import com.kamil.challenge3.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.button.setOnClickListener {
            val name = binding.edtName.text.toString()

            if (binding.edtName.text.isNullOrEmpty()){
                Toast.makeText(requireContext(), "Masukan Nama Anda !", Toast.LENGTH_SHORT).show()
            } else{
                val identity = Identity(name)
                val action = SecondFragmentDirections.actionSecondFragmentToThridFragment(identity)
                findNavController().navigate(action)
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}