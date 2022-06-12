package com.kamil.challengechapter4.view.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.kamil.challengechapter4.databinding.FragmentAddStocksBinding
import com.kamil.challengechapter4.model.StocksDatabase
import com.kamil.challengechapter4.model.database.StocksEntity
import com.kamil.challengechapter4.model.utils.Constant
import com.kamil.challengechapter4.model.utils.SharedPreference
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

@DelicateCoroutinesApi
class AddStocksFragment : DialogFragment(){
    private var bind : FragmentAddStocksBinding? = null
    private val binding get() = bind!!
    private var dataStocks : StocksDatabase? = null
    private lateinit var shared : SharedPreference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bind = FragmentAddStocksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shared = SharedPreference(requireContext())
        dataStocks = StocksDatabase.getData(requireContext())

        binding.apply {
            btnAddStocks.setOnClickListener {
                val codeStocks = etCodeStocks.text.toString()
                val nameStocks = etNameStocks.text.toString()
                val equityStocks = etEquityStocks.text.toString().toInt()
                val netProfitStocks = etNetProfitStocks.text.toString().toInt()
                val priceStocks = etPriceShareStocks.text.toString().toInt()
                val sharesStocks = etShareStocks.text.toString().toInt()
                val bvStocks = equityStocks/sharesStocks
                val pbvStocks = priceStocks/bvStocks.toDouble()
                val epsStocks = netProfitStocks.toDouble()/sharesStocks
                val user = shared.getString(Constant.USER)

                val objStocks = StocksEntity(
                    null,
                    codeStocks,
                    nameStocks,
                    equityStocks,
                    netProfitStocks,
                    pbvStocks,
                    epsStocks,
                    priceStocks,
                    sharesStocks,
                    user
                )

                GlobalScope.async {
                    dataStocks?.stocksDao()?.insertDataStocks(objStocks)
                }
                Toast.makeText(requireContext(), "Add Stocks Success", Toast.LENGTH_SHORT).show()
                dialog?.dismiss()
            }

            btnCancelSave.setOnClickListener {
                Toast.makeText(requireContext(), "You have canceled additional stocks", Toast.LENGTH_SHORT).show()
                dialog?.dismiss()
            }
        }
    }
}