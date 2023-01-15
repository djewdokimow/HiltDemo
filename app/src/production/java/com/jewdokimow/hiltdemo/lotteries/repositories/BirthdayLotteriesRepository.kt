package com.jewdokimow.hiltdemo.lotteries.repositories

import com.jewdokimow.hiltdemo.lotteries.LotteriesAppData
import com.jewdokimow.hiltdemo.lotteries.models.LotteryResult
import com.jewdokimow.hiltdemo.lotteries.models.BirthdayLottery
import com.jewdokimow.hiltdemo.lotteries.utilities.IDrawLotteryEngine
import com.jewdokimow.hiltdemo.lotteries.utilities.ILotteriesValidator
import java.time.LocalDate

class BirthdayLotteriesRepository(
    private val appUserData: LotteriesAppData,
    private val lotteryEngine: IDrawLotteryEngine<BirthdayLottery>,
    private val validator: ILotteriesValidator
) : ILotteryRepository<BirthdayLottery> {

    override fun getLotteriesAvailableForUser(): List<BirthdayLottery> {
        return appUserData.userLotteries.filter {
            it is BirthdayLottery && validator.shouldBeLotteryVisibleForUser(
                LocalDate.ofEpochDay(System.currentTimeMillis()), it
            )
        } as List<BirthdayLottery>
    }

    override fun getLotteriesVisibleForUser(): List<BirthdayLottery> {
        return appUserData.userLotteries.filter {
            it is BirthdayLottery && validator.shouldBoLotteryAvailableTodayForUser(
                LocalDate.ofEpochDay(System.currentTimeMillis()), it
            )
        } as List<BirthdayLottery>
    }

    override fun drawLottery(
        lottery: BirthdayLottery
    ): LotteryResult {
        return lotteryEngine.drawLottery(lottery).also {
            appUserData.userLotteries -= lottery
        }
    }
}