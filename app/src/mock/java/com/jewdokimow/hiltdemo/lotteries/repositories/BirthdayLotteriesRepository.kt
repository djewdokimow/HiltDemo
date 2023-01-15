package com.jewdokimow.hiltdemo.lotteries.repositories

import com.jewdokimow.hiltdemo.lotteries.LotteryResult
import com.jewdokimow.hiltdemo.lotteries.models.BirthdayLottery

class BirthdayLotteriesRepository : ILotteryRepository<BirthdayLottery> {

    override fun getLotteriesAvailableForUser(): List<BirthdayLottery> {
        TODO("Not yet implemented")
    }

    override fun getLotteriesVisibleForUser(): List<BirthdayLottery> {
        TODO("Not yet implemented")
    }

    override fun drawLottery(): LotteryResult {
        TODO("Not yet implemented")
    }
}