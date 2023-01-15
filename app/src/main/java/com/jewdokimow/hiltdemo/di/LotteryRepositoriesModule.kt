package com.jewdokimow.hiltdemo.di

import com.jewdokimow.hiltdemo.lotteries.LotteriesAppData
import com.jewdokimow.hiltdemo.lotteries.models.LotteryResult
import com.jewdokimow.hiltdemo.lotteries.models.Reward
import com.jewdokimow.hiltdemo.lotteries.models.BirthdayLottery
import com.jewdokimow.hiltdemo.lotteries.models.DailyLottery
import com.jewdokimow.hiltdemo.lotteries.repositories.BirthdayLotteriesRepository
import com.jewdokimow.hiltdemo.lotteries.repositories.DailyLotteriesRepository
import com.jewdokimow.hiltdemo.lotteries.repositories.ILotteryRepository
import com.jewdokimow.hiltdemo.lotteries.utilities.IDrawLotteryEngine
import com.jewdokimow.hiltdemo.lotteries.utilities.LotteriesValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import java.util.Random

@Module
@InstallIn(FragmentComponent::class)
class LotteryRepositoriesModule {

    @FragmentScoped
    @Provides
    fun provideBirthdayLotteryDrawingEngine(random: Random): IDrawLotteryEngine<BirthdayLottery> {
        return object : IDrawLotteryEngine<BirthdayLottery> {
            override fun drawLottery(lottery: BirthdayLottery): LotteryResult {
                val randomCount = Math.abs(random.nextInt()) % 10
                return when (randomCount) {
                    0, 1, 2, 3, 4 -> LotteryResult.Success(Reward("Wygrales 20% rabatu! Wszystkiego najlepszego"))
                    5, 6, 7, 8 -> LotteryResult.NewTicket
                    else -> LotteryResult.Failure
                }
            }
        }
    }

    @FragmentScoped
    @Provides
    fun provideDailyLotteryDrawingEngine(random: Random): IDrawLotteryEngine<DailyLottery> {
        return object : IDrawLotteryEngine<DailyLottery> {
            override fun drawLottery(lottery: DailyLottery): LotteryResult {
                val randomCount = Math.abs(random.nextInt()) % 10
                return when (randomCount) {
                    0, 1, 2 -> LotteryResult.Success(Reward("Wygrales 10% rabatu!"))
                    3, 4 -> LotteryResult.NewTicket
                    else -> LotteryResult.Failure
                }
            }
        }
    }

    @FragmentScoped
    @Provides
    fun provideLotteriesValidator(): LotteriesValidator {
        return LotteriesValidator()
    }

    @FragmentScoped
    @Provides
    fun provideBirthdayLotteriesRepository(
        data: LotteriesAppData,
        lotteryEngine: IDrawLotteryEngine<BirthdayLottery>,
        validator: LotteriesValidator
    ): ILotteryRepository<BirthdayLottery> {
        return BirthdayLotteriesRepository(data, lotteryEngine, validator)
    }

    @FragmentScoped
    @Provides
    fun provideDailyLotteriesRepository(
        data: LotteriesAppData,
        lotteryEngine: IDrawLotteryEngine<DailyLottery>,
        validator: LotteriesValidator
    ): ILotteryRepository<DailyLottery> {
        return DailyLotteriesRepository(data, lotteryEngine, validator)
    }
}