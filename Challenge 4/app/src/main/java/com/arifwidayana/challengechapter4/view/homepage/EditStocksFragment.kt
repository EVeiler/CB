package com.kamil.challengechapter4.view.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.kamil.challengechapter4.databinding.FragmentEditStocksBinding
import com.kamil.challengechapter4.model.StocksDatabase
import com.kamil.challengechapter4.model.database.StocksEntity
import com.kamil.challengechapter4.model.utils.SharedPreference
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

@DelicateCoroutinesApi
class EditStocksFragment() : DialogFragment() {
    private var bind : FragmentEditStocksBinding? = null
    private val binding get() = bind!!
    private var dataStocks : StocksDatabase? = null
    private lateinit var shared : SharedPreference
    lateinit var listStock : StocksEntity

    constructor(liststocks: StocksEntity): this() {
        this.listStock = liststocks
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bind = FragmentEditStocksBinding.inflate(inflater, container, false)
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


        if (this::listStock.isInitialized) {
            binding.apply {
                etEditCodeStocks.setText(listStock.codeStock.toString())
                etEditNameStocks.setText(listStock.nameStock.toString())
                etEditEquityStocks.setText(listStock.valueEquity.toString())
                etEditNetProfitStocks.setText(listStock.valueNetProfit.toString())
                etPbvStocks.setText(listStock.priceBookValue.toString())
                etEpsStocks.setText(listStock.earningsPerShare.toString())
                etEditPriceShareStocks.setText(listStock.sharePrice.toString())
                etEditShareStocks.setText(listStock.shareStock.toString())
            }
        }
        binding.apply {
            btnEditStocks.setOnClickListener {
                val codeStocks =  etEditCodeStocks.text.toString()
                val nameStocks = etEditNameStocks.text.toString()
                val equityStocks = etEditEquityStocks.text.toString().toInt()
                val netProfitStocks = etEditNetProfitStocks.text.toString().toInt()
                val priceStocks = etEditPriceShareStocks.text.toString().toInt()
                val sharesStocks = etEditShareStocks.text.toString().toInt()
                val bvStocks = equityStocks/sharesStocks
                val pbvStocks = priceStocks/bvStocks.toDouble()
                val epsStocks = netProfitStocks.toDouble()/sharesStocks
                listStock.apply {
                    codeStock = codeStocks
                    nameStock = nameStocks
                    valueEquity = equityStocks
                    valueNetProfit= netProfitStocks
                    sharePrice = priceStocks
                    shareStock = sharesStocks
                    priceBookValue = pbvStocks
                    earningsPerShare = epsStocks
                }
                GlobalScope.async {
                    dataStocks?.stocksDao()?.updateDataStocks(listStock)
                }
                Toast.makeText(requireContext(), "Edit Stocks Success", Toast.LENGTH_SHORT).show()
                dialog?.dismiss()
            }

            btnDelete.setOnClickListener {
                GlobalScope.async {
                    dataStocks?.stocksDao()?.deleteDataStocks(listStock)
                }
                Toast.makeText(requireContext(), "Delete Stocks Success", Toast.LENGTH_SHORT).show()
                dialog?.dismiss()
            }
        }
    }

}