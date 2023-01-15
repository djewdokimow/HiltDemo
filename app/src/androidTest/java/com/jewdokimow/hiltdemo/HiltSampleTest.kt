package com.jewdokimow.hiltdemo

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jewdokimow.hiltdemo.di.LotteriesGeneratorModule
import com.jewdokimow.hiltdemo.di.LotteryAppModule
import com.jewdokimow.hiltdemo.lotteries.LotteriesAppData
import com.jewdokimow.hiltdemo.lotteries.models.DailyLottery
import com.jewdokimow.hiltdemo.lotteries.models.UserData
import com.jewdokimow.hiltdemo.lotteries.repositories.ILotteryRepository
import com.jewdokimow.hiltdemo.lotteries.utilities.ILotteriesGeneratorUtil
import dagger.hilt.android.testing.*
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDate
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@UninstallModules(LotteryAppModule::class)
class HiltSampleTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @BindValue
    val appData: LotteriesAppData = LotteriesAppData(
        userLotteries = listOf(
            DailyLottery(
                LocalDate.of(1995, 2, 21),
                LocalDate.of(1995, 2, 21),
                LocalDate.of(1995, 2, 21),
                LocalDate.of(1995, 2, 21)
            )
        ),
        userData = UserData("tester", LocalDate.of(1995, 2, 21))
    )


    @Inject
    lateinit var dailyLottery: ILotteryRepository<DailyLottery>

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun sampleTest() {
        assertEquals(
            dailyLottery.getLotteriesAvailableForUser(),
            listOf(
                DailyLottery(
                    LocalDate.of(1995, 2, 21),
                    LocalDate.of(1995, 2, 21),
                    LocalDate.of(1995, 2, 21),
                    LocalDate.of(1995, 2, 21)
                )
            )
        )
    }
}