package com.jewdokimow.hiltdemo.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.jewdokimow.hiltdemo.databinding.ItemLotteryBinding
import com.jewdokimow.hiltdemo.lotteries.models.BaseLottery

class LotteryAdapter<T : BaseLottery>(private val onLotterySelected: (T) -> Unit) :
    RecyclerView.Adapter<LotteryItemViewHolder<T>>() {

    var data: List<T> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LotteryItemViewHolder<T> {
        val itemBinding =
            ItemLotteryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LotteryItemViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: LotteryItemViewHolder<T>, position: Int) {
        val item = data[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onLotterySelected(item)
        }
    }
}

class LotteryItemViewHolder<T : BaseLottery>(private val itemLotteryBinding: ItemLotteryBinding) :
    ViewHolder(itemLotteryBinding.root) {
    fun bind(data: T) {
        itemLotteryBinding.label.text = data.prepareLabel()
    }
}