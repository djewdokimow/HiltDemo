package com.jewdokimow.hiltdemo.lotteries.repositories

import com.jewdokimow.hiltdemo.lotteries.models.LotteryResult
import com.jewdokimow.hiltdemo.lotteries.models.BaseLottery

interface ILotteryRepository<T : BaseLottery> {
    fun getLotteriesAvailableForUser(): List<T>
    fun getLotteriesVisibleForUser(): List<T>
    fun drawLottery(
        lottery: T
    ): LotteryResult
}