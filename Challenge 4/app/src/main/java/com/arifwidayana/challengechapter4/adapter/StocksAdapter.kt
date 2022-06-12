package com.kamil.challengechapter4.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kamil.challengechapter4.MainActivity
import com.kamil.challengechapter4.databinding.ItemStocksBinding
import com.kamil.challengechapter4.model.database.StocksEntity
import com.kamil.challengechapter4.view.homepage.EditStocksFragment
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
class StocksAdapter: RecyclerView.Adapter<StocksAdapter.StockHolder>() {
    private val listStocks = mutableListOf<StocksEntity>()
    class StockHolder(val binding: ItemStocksBinding): RecyclerView.ViewHolder(binding.root)

    private lateinit var onClickCallback: OnItemClickCallback

    fun setOnClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockHolder {
        val binding = ItemStocksBinding.inflate(LayoutInflater.from(parent.context))
        return StockHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: StockHolder, position: Int) {
        with(holder.binding) {
            tvCodeStocks.text = listStocks[position].codeStock
            tvNameStocks.text = listStocks[position].nameStock
            tvEquityStocks.text = "Equity: ${listStocks[position].valueEquity.toString()}"
            tvNetProfitStocks.text = "Net Profit: ${listStocks[position].valueNetProfit.toString()}"
            tvEpsStocks.text = "EPS: ${listStocks[position].earningsPerShare.toString()}"
            tvPbvStocks.text = "PBV: ${listStocks[position].priceBookValue.toString()}"
            tvSharesStocks.text = "Total Shares: ${listStocks[position].shareStock.toString()}"
            tvSharePriceStocks.text = "+${listStocks[position].sharePrice.toString()}"

            rvList.setOnClickListener {
                val activity = it.context as MainActivity
                val sendFrag = EditStocksFragment(listStocks[position])
                sendFrag.show(activity.supportFragmentManager, null)
            }
        }
    }

    override fun getItemCount(): Int {
        return listStocks.size
    }

    interface OnItemClickCallback{
        fun onItemClicked(dataStocks: StocksEntity)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData (listStock: List<StocksEntity>){
        listStocks.clear()
        listStocks.addAll(listStock)
        notifyDataSetChanged()
    }
}
