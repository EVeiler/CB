package com.kamil.challengechapter4.view.homepage

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kamil.challengechapter4.R
import com.kamil.challengechapter4.adapter.StocksAdapter
import com.kamil.challengechapter4.databinding.FragmentHomepageBinding
import com.kamil.challengechapter4.model.StocksDatabase
import com.kamil.challengechapter4.model.database.StocksEntity
import com.kamil.challengechapter4.model.utils.Constant
import com.kamil.challengechapter4.model.utils.SharedPreference
import kotlinx.coroutines.*

@DelicateCoroutinesApi
class HomepageFragment : Fragment() {
    private var bind : FragmentHomepageBinding? = null
    private val binding get() = bind!!
    private var stocks : StocksDatabase? = null
    private lateinit var shared : SharedPreference
    private lateinit var adapter: StocksAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bind = FragmentHomepageBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shared = SharedPreference(requireContext())
        stocks = StocksDatabase.getData(requireContext())
        adapter = StocksAdapter()

        binding.rvStocks.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvStocks.adapter = adapter

        showStocks()
        binding.apply {
            tvName.text = "Hi ${shared.getString(Constant.USER)}"

            fabAddStocks.setOnClickListener {
                AddStocksFragment().show(childFragmentManager, null)
            }

            ivLogout.setOnClickListener {
                shared.clear()
                Toast.makeText(requireContext(), "Logout Success", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_homepageFragment_to_loginFragment)
            }
        }
        binding.ivRefresh.setOnClickListener {
            showStocks()
        }
    }

    override fun onResume() {
        super.onResume()
        showStocks()
    }

    override fun onDestroy() {
        super.onDestroy()
        bind = null
    }

    private fun showStocks() {
        GlobalScope.launch {
            val listStocks = stocks?.stocksDao()?.getDataStocks(shared.getString(Constant.USER)!!)
            runBlocking(Dispatchers.Main) {
                listStocks?.let {
                    adapter.setData(it)
                }
            }
        }

        adapter.setOnClickCallback(object : StocksAdapter.OnItemClickCallback{
            override fun onItemClicked(dataStocks: StocksEntity) {
                EditStocksFragment().show(childFragmentManager, null)
            }
        })
    }
}