package com.jewdokimow.hiltdemo

import com.jewdokimow.hiltdemo.lotteries.LotteriesAppData
import com.jewdokimow.hiltdemo.lotteries.models.BirthdayLottery
import com.jewdokimow.hiltdemo.lotteries.models.DailyLottery
import com.jewdokimow.hiltdemo.lotteries.models.UserData
import com.jewdokimow.hiltdemo.lotteries.utilities.LotteriesGeneratorUtil
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.time.LocalDate

@RunWith(MockitoJUnitRunner::class)
class GeneratorTests {

    @Mock
    lateinit var timeEngine: TimeEngine

    @Before
    fun initMocks() {
        Mockito.`when`(timeEngine.getCurrentTime())
            .thenReturn(
                LocalDate.of(2025, 7, 27)
            )
    }

    @Test
    fun hasUserBirthdayTest() {
        val userData = LotteriesAppData(
            userData = UserData("Dawid", LocalDate.of(1993, 7, 27))
        )
        val generator = LotteriesGeneratorUtil(
            userData,
            timeEngine
        )
        generator.generateUserTickets()
        assertEquals(
            listOf(
                BirthdayLottery(
                    visibilityStartDate = LocalDate.of(2025, 7, 26),
                    visibilityEndDate = LocalDate.of(2025, 7, 28),
                    validityStartDate = LocalDate.of(2025, 7, 27),
                    validityEndDate = LocalDate.of(2025, 7, 28)
                ),
                DailyLottery(
                    visibilityStartDate = LocalDate.of(2025, 7, 26),
                    visibilityEndDate = LocalDate.of(2025, 7, 28),
                    validityStartDate = LocalDate.of(2025, 7, 27),
                    validityEndDate = LocalDate.of(2025, 7, 28)
                ),
                DailyLottery(
                    visibilityStartDate = LocalDate.of(2025, 7, 27),
                    visibilityEndDate = LocalDate.of(2025, 7, 29),
                    validityStartDate = LocalDate.of(2025, 7, 28),
                    validityEndDate = LocalDate.of(2025, 7, 29)
                )
            ),
            userData.userLotteries
        )
    }

    @Test
    fun hasNotUserBirthdayTest() {
        val userData = LotteriesAppData(
            userData = UserData("M", LocalDate.of(1997, 3, 14))
        )
        val generator = LotteriesGeneratorUtil(
            userData,
            timeEngine
        )
        generator.generateUserTickets()
        assertEquals(
            listOf(
                DailyLottery(
                    visibilityStartDate = LocalDate.of(2025, 7, 26),
                    visibilityEndDate = LocalDate.of(2025, 7, 28),
                    validityStartDate = LocalDate.of(2025, 7, 27),
                    validityEndDate = LocalDate.of(2025, 7, 28)
                ),
                DailyLottery(
                    visibilityStartDate = LocalDate.of(2025, 7, 27),
                    visibilityEndDate = LocalDate.of(2025, 7, 29),
                    validityStartDate = LocalDate.of(2025, 7, 28),
                    validityEndDate = LocalDate.of(2025, 7, 29)
                )
            ),
            userData.userLotteries
        )
    }
}