package com.jewdokimow.hiltdemo.di

import com.jewdokimow.hiltdemo.TimeEngine
import com.jewdokimow.hiltdemo.lotteries.LotteriesAppData
import com.jewdokimow.hiltdemo.lotteries.utilities.ILotteriesGeneratorUtil
import com.jewdokimow.hiltdemo.lotteries.utilities.LotteriesGeneratorUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LotteriesGeneratorModule {

    @Singleton
    @Provides
    fun provideRandom(): Random {
        return Random(System.currentTimeMillis())
    }

    @Singleton
    @Provides
    fun provideLotteriesGeneratorUtil(
        data: LotteriesAppData,
        timeEngine: TimeEngine
    ): ILotteriesGeneratorUtil {
        return LotteriesGeneratorUtil(data, timeEngine)
    }
}