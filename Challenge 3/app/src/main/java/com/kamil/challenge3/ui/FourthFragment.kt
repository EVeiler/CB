package com.kamil.challenge3.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kamil.challenge3.data.Identity
import com.kamil.challenge3.databinding.FragmentFourthBinding

class FourthFragment : Fragment() {

    private var _binding: FragmentFourthBinding? = null
    private val binding get() = _binding!!

    private val args: FourthFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFourthBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            edtName.setText(args.name)
            button.setOnClickListener {
                val name = edtName.text.toString()
                val usia = edtUsia.text.toString()
                val alamat = edtAlamat.text.toString()
                val pekerjaan = edtPekerjaan.text.toString()

                if (edtName.text.isNullOrEmpty() || edtUsia.text.isNullOrEmpty() || edtAlamat.text.isNullOrEmpty() || edtPekerjaan.text.isNullOrEmpty()) {
                    Toast.makeText(requireContext(), "Lengkapi Data Anda !", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    val identity = Identity(name, usia.toInt(), alamat, pekerjaan)
                    findNavController().navigate(
                        FourthFragmentDirections.actionFourthFragmentToThridFragment(
                            identity
                        )
                    )

                }
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}