package com.jewdokimow.hiltdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jewdokimow.hiltdemo.databinding.FragmentDailyLotteriesBinding
import com.jewdokimow.hiltdemo.lotteries.models.LotteryResult
import com.jewdokimow.hiltdemo.lotteries.models.DailyLottery
import com.jewdokimow.hiltdemo.lotteries.repositories.ILotteryRepository
import com.jewdokimow.hiltdemo.ui.LotteryAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DailyLotteryFragment : Fragment() {

    private lateinit var binding: FragmentDailyLotteriesBinding

    @Inject
    lateinit var lotteryRepository: ILotteryRepository<DailyLottery>

    private lateinit var adapter: LotteryAdapter<DailyLottery>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDailyLotteriesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = LotteryAdapter {
            val message = when (val result = lotteryRepository.drawLottery(it)) {
                is LotteryResult.Success -> result.data.label
                LotteryResult.NewTicket -> "Zagraj jeszcze raz!"
                else -> "Przegrales!"
            }
            Toast.makeText(context, message, Toast.LENGTH_SHORT)
                .show()
            reloadData()
        }
        reloadData()
        binding.lotteriesRecyclerView.apply {
            this.adapter = this@DailyLotteryFragment.adapter
            this.layoutManager = LinearLayoutManager(context)
        }
    }

    fun reloadData() {
        adapter.data = lotteryRepository.getLotteriesAvailableForUser()
        adapter.notifyDataSetChanged()
    }
}