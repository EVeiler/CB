package com.kamil.challenge3.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kamil.challenge3.R
import com.kamil.challenge3.databinding.FragmentThridBinding

class ThridFragment : Fragment() {

    private var _binding: FragmentThridBinding? = null
    private val binding
        get() = _binding!!

    private val args: ThridFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThridBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = args.identity?.name // sebuah nama yang dikirimkan oleh second fragment
        val usia = args.identity?.usia
        val alamat = args.identity?.alamat
        val pekerjaan = args.identity?.pekerjaan

        binding.btnScreen3.setOnClickListener {
            findNavController().navigate(ThridFragmentDirections.actionThridFragmentToFourthFragment(name!!))
        }

        if (alamat == null && pekerjaan == null && usia == null){
            binding.txtYourname.text = name
        } else {
            with(binding){
                txtYourname.visibility = View.INVISIBLE
                binding.viewFullIdentity.identityDetail.visibility = View.VISIBLE
                binding.viewFullIdentity.txtFullname.text = name
                binding.viewFullIdentity.txtAlamat.text = alamat
                binding.viewFullIdentity.txtPekerjaan.text = pekerjaan

                if (args.identity!!.usia?.rem(2) == 0){
                    viewFullIdentity.txtAge.text = getString(R.string.umur, usia, "genap")
                } else {
                    viewFullIdentity.txtAge.text = getString(R.string.umur, usia, "ganjil")
                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}