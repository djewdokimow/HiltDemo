package com.jewdokimow.hiltdemo.lotteries.utilities

import android.service.autofill.Validators.and
import com.jewdokimow.hiltdemo.lotteries.models.BaseLottery
import java.time.LocalDate

class LotteriesValidator : ILotteriesValidator {

    override fun shouldBeLotteryVisibleForUser(
        currentDate: LocalDate,
        lottery: BaseLottery
    ): Boolean {
        return (currentDate.isAfter(lottery.visibilityStartDate)
            .or(currentDate.isEqual(lottery.visibilityStartDate)))
            .and(currentDate.isBefore(lottery.visibilityEndDate))
    }

    override fun shouldBoLotteryAvailableTodayForUser(
        currentDate: LocalDate,
        lottery: BaseLottery
    ): Boolean {
        return (currentDate.isAfter(lottery.validityStartDate)
            .or(currentDate.isEqual(lottery.validityStartDate)))
            .and(currentDate.isBefore(lottery.validityEndDate))
    }
}