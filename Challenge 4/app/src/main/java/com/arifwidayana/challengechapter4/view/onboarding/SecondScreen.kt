package com.kamil.challengechapter4.view.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.kamil.challengechapter4.R
import com.kamil.challengechapter4.databinding.FragmentSecondScreenBinding

class SecondScreen : Fragment() {
    private var bind: FragmentSecondScreenBinding? = null
    private val binding get() = bind!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind = FragmentSecondScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val secondScreen = activity?.findViewById<ViewPager2>(R.id.vp_on_boarding)
        binding.tvSecondStep.setOnClickListener {
            secondScreen?.currentItem = 2
        }
    }
}