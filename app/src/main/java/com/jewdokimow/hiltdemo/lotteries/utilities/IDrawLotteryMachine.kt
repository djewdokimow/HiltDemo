package com.jewdokimow.hiltdemo.lotteries.utilities

import com.jewdokimow.hiltdemo.lotteries.models.LotteryResult
import com.jewdokimow.hiltdemo.lotteries.models.BaseLottery

interface IDrawLotteryEngine<T : BaseLottery> {
    fun drawLottery(lottery: T): LotteryResult
}