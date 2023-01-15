package com.jewdokimow.hiltdemo.di

import com.jewdokimow.hiltdemo.lotteries.LotteriesAppData
import com.jewdokimow.hiltdemo.lotteries.models.UserData
import com.jewdokimow.hiltdemo.lotteries.utilities.ILotteriesGeneratorUtil
import com.jewdokimow.hiltdemo.lotteries.utilities.LotteriesGeneratorUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.time.LocalDate
import java.util.Random
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LotteryAppModule {

    @Singleton
    @Provides
    fun provideRandom(): Random {
        return Random(System.currentTimeMillis())
    }

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
    fun provideLotteriesGeneratorUtil(data: LotteriesAppData): ILotteriesGeneratorUtil {
        return LotteriesGeneratorUtil(data)
    }
}