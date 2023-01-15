package com.jewdokimow.hiltdemo.lotteries.utilities

import com.jewdokimow.hiltdemo.lotteries.models.BaseLottery
import java.time.LocalDate

class LotteriesValidator : ILotteriesValidator {

    override fun shouldBeLotteryVisibleForUser(
        currentDate: LocalDate,
        lottery: BaseLottery
    ): Boolean {
        return currentDate.isAfter(lottery.visibilityStartDate)
            .and(currentDate.isBefore(lottery.visibilityEndDate))
    }

    override fun shouldBoLotteryAvailableTodayForUser(
        currentDate: LocalDate,
        lottery: BaseLottery
    ): Boolean {
        return currentDate.isAfter(lottery.validityStartDate)
            .and(currentDate.isBefore(lottery.validityEndDate))
    }
}