package com.jewdokimow.hiltdemo.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.jewdokimow.hiltdemo.databinding.ItemLotteryBinding
import com.jewdokimow.hiltdemo.lotteries.models.BaseLottery
import com.jewdokimow.hiltdemo.lotteries.models.LotteryResult
import com.jewdokimow.hiltdemo.lotteries.repositories.ILotteryRepository
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class LotteryAdapter<T : BaseLottery> @Inject constructor(
    @ActivityContext val context: Context,
    private val lotteryRepository: ILotteryRepository<T>
) :
    RecyclerView.Adapter<LotteryItemViewHolder<T>>() {

    var data: List<T> = emptyList()
    lateinit var reloadData: () -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LotteryItemViewHolder<T> {
        val itemBinding =
            ItemLotteryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LotteryItemViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: LotteryItemViewHolder<T>, position: Int) {
        val item = data[position] as T
        holder.bind(item)
        holder.itemView.setOnClickListener {
            val message = when (val result = lotteryRepository.drawLottery(item)) {
                is LotteryResult.Success -> result.data.label
                LotteryResult.NewTicket -> "Zagraj jeszcze raz!"
                else -> "Przegrales!"
            }
            Toast.makeText(context, message, Toast.LENGTH_SHORT)
                .show()
            reloadData()
        }
    }
}

class LotteryItemViewHolder<T : BaseLottery>(private val itemLotteryBinding: ItemLotteryBinding) :
    ViewHolder(itemLotteryBinding.root) {
    fun bind(data: T) {
        itemLotteryBinding.label.text = data.prepareLabel()
    }
}