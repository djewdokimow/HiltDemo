package com.jewdokimow.hiltdemo.lotteries.repositories

import com.jewdokimow.hiltdemo.TimeEngine
import com.jewdokimow.hiltdemo.lotteries.LotteriesAppData
import com.jewdokimow.hiltdemo.lotteries.models.LotteryResult
import com.jewdokimow.hiltdemo.lotteries.models.DailyLottery
import com.jewdokimow.hiltdemo.lotteries.utilities.IDrawLotteryEngine
import com.jewdokimow.hiltdemo.lotteries.utilities.ILotteriesValidator
import java.time.LocalDate

class DailyLotteriesRepository(
    private val appUserData: LotteriesAppData,
    private val lotteryEngine: IDrawLotteryEngine<DailyLottery>,
    private val validator: ILotteriesValidator,
    private val timeEngine: TimeEngine
) : ILotteryRepository<DailyLottery> {

    override fun getLotteriesAvailableForUser(): List<DailyLottery> {
        return appUserData.userLotteries.filter {
            it is DailyLottery && validator.shouldBeLotteryVisibleForUser(
                timeEngine.getCurrentTime(),
                it
            )
        } as List<DailyLottery>
    }

    override fun getLotteriesVisibleForUser(): List<DailyLottery> {
        return appUserData.userLotteries.filter {
            it is DailyLottery && validator.shouldBoLotteryAvailableTodayForUser(
                timeEngine.getCurrentTime(), it
            )
        } as List<DailyLottery>
    }

    override fun drawLottery(
        lottery: DailyLottery
    ): LotteryResult {
        return lotteryEngine.drawLottery(lottery).also {
            if (it != LotteryResult.NewTicket) {
                appUserData.userLotteries -= lottery
            }
        }
    }
}