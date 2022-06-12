package com.kamil.challengechapter4.view.login

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.kamil.challengechapter4.R
import com.kamil.challengechapter4.databinding.FragmentLoginBinding
import com.akamil.challengechapter4.model.StocksDatabase
import com.kamil.challengechapter4.model.utils.Constant
import com.kamil.challengechapter4.model.utils.SharedPreference
import kotlinx.coroutines.*

class LoginFragment : Fragment() {
    private var bind: FragmentLoginBinding? = null
    private val binding get() = bind!!
    private var dataUser: StocksDatabase? = null
    lateinit var shared : SharedPreference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bind = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shared = SharedPreference(requireContext())
        dataUser = StocksDatabase.getData(requireContext())

        binding.apply {
            btnLogin.setOnClickListener {
                val user = etUsername.text.toString()
                val pass = etPassword.text.toString()

                when {
                    user.isEmpty() && pass.isEmpty() -> {
                        tfUsername.error = "Fill this Username"
                        tfPassword.error = "Fill this Password"
                    }
                    user.isEmpty() -> tfUsername.error = "Fill this Username"
                    pass.isEmpty() -> tfPassword.error = "Fill this Password"
                    else -> {
                        CoroutineScope(Dispatchers.Main).launch {
                            val usersNames = dataUser?.userDao()?.getUsername(user)
                            when (usersNames?.password) {
                                pass -> {
                                    loginSession(user, pass)
                                    Toast.makeText(requireContext(), "Login Success", Toast.LENGTH_SHORT).show()
                                    findNavController().navigate(R.id.action_loginFragment_to_homepageFragment)
                                }
                                else -> Toast.makeText(requireContext(), "Wrong password!", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }

            tvSignUp.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }

        }
    }

    override fun onStart() {
        super.onStart()
        if (shared.getBoolean(Constant.IS_LOGIN)){
            findNavController().navigate(R.id.action_loginFragment_to_homepageFragment)
        }
    }

    private fun loginSession(username: String, password: String) {
        binding.apply {
            shared.put(Constant.USER, username)
            shared.put(Constant.PASS, password)
            shared.put(Constant.IS_LOGIN, true)
        }
    }
}