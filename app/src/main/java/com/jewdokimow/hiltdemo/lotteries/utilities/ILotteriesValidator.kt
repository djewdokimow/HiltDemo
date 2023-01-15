package com.jewdokimow.hiltdemo.lotteries.utilities

import com.jewdokimow.hiltdemo.lotteries.models.BaseLottery
import java.time.LocalDate

interface ILotteriesValidator {
    fun shouldBeLotteryVisibleForUser(
        currentDate: LocalDate,
        lottery: BaseLottery
    ): Boolean

    fun shouldBoLotteryAvailableTodayForUser(
        currentDate: LocalDate,
        lottery: BaseLottery
    ): Boolean
}