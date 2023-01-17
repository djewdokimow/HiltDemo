package com.jewdokimow.hiltdemo.lotteries.repositories

import com.jewdokimow.hiltdemo.lotteries.LotteriesAppData
import com.jewdokimow.hiltdemo.lotteries.models.LotteryResult
import com.jewdokimow.hiltdemo.lotteries.models.DailyLottery
import com.jewdokimow.hiltdemo.lotteries.utilities.IDrawLotteryEngine
import com.jewdokimow.hiltdemo.lotteries.utilities.ILotteriesValidator
import java.time.LocalDate

class DailyLotteriesRepository(
    private val appUserData: LotteriesAppData,
    private val lotteryEngine: IDrawLotteryEngine<DailyLottery>,
    private val validator: ILotteriesValidator
) : ILotteryRepository<DailyLottery> {

    override fun getLotteriesAvailableForUser(): List<DailyLottery> {
        return appUserData.userLotteries.filter {
            it is DailyLottery && validator.shouldBeLotteryVisibleForUser(LocalDate.now(), it)
        } as List<DailyLottery>
    }

    override fun getLotteriesVisibleForUser(): List<DailyLottery> {
        return appUserData.userLotteries.filter {
            it is DailyLottery && validator.shouldBoLotteryAvailableTodayForUser(
                LocalDate.now(), it
            )
        } as List<DailyLottery>
    }

    override fun drawLottery(
        lottery: DailyLottery
    ): LotteryResult {
        return lotteryEngine.drawLottery(lottery).also {
            appUserData.userLotteries -= lottery
        }
    }
}