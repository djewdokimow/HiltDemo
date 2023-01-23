package com.jewdokimow.hiltdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jewdokimow.hiltdemo.databinding.FragmentDailyLotteriesBinding
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

    @Inject
    lateinit var adapter: LotteryAdapter<DailyLottery>

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
        reloadData()
        binding.lotteriesRecyclerView.apply {
            this.adapter = this@DailyLotteryFragment.adapter
            this@DailyLotteryFragment.adapter.reloadData = ::reloadData
            this.layoutManager = LinearLayoutManager(context)
        }
    }

    private fun reloadData() {
        adapter.data = lotteryRepository.getLotteriesAvailableForUser()
        adapter.notifyDataSetChanged()
    }
}

//
//class DailyLotteryNoDiModule {
//    private var lotteryRepository = DailyLotteriesRepository()
//}
//
//class DailyLotteryConstructorDiModule(private var lotteryRepository: DailyLotteriesRepository)
//
//class DailyLotteryFieldDiModule {
//    lateinit var lotteryRepository: DailyLotteriesRepository
//}
//
//class DailyLotterySetterMethodDiModule {
//    private lateinit var lotteryRepository: DailyLotteriesRepository
//
//    fun initWithLotteryRepository(lotteryRepository: DailyLotteriesRepository) {
//        this.lotteryRepository = lotteryRepository
//    }
//}
//
//class DailyLotteryMethodDiModule {
//    private lateinit var lotteryRepository: ILotteryRepository<DailyLottery>
//
//    fun initWithLotteryRepository(lotteryRepository: ILotteryRepository<DailyLottery>) {
//        this.lotteryRepository = lotteryRepository
//    }
//}
//
//class DailyLotterySelfDiModule {
//    lateinit var lotteryRepository: ILotteryRepository<DailyLottery>
//    init {
//        SelfDiFactory.inject(this)
//    }
//}
//
//object SelfDiFactory {
//    fun inject(lotteryModule: DailyLotterySelfDiModule){
//        lotteryModule.lotteryRepository = DailyLotteriesRepository()
//    }
//}