package com.jewdokimow.hiltdemo.di

import com.jewdokimow.hiltdemo.TimeEngine
import com.jewdokimow.hiltdemo.lotteries.LotteriesAppData
import com.jewdokimow.hiltdemo.lotteries.models.UserData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.time.LocalDate
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LotteryAppModule {

    @Singleton
    @Provides
    fun provideLotteriesAppData(): LotteriesAppData {
        return LotteriesAppData(
            userData = UserData(
                name = "Dawid",
                birthday = LocalDate.of(1993, 7, 27)
            )
        )
    }

    @Singleton
    @Provides
    fun getTimeEngine(): TimeEngine {
        return TimeEngine()
    }
}