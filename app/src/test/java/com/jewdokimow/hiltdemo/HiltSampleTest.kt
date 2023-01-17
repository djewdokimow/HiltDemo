package com.jewdokimow.hiltdemo

import com.jewdokimow.hiltdemo.lotteries.LotteriesAppData
import com.jewdokimow.hiltdemo.lotteries.models.*
import com.jewdokimow.hiltdemo.lotteries.repositories.DailyLotteriesRepository
import com.jewdokimow.hiltdemo.lotteries.utilities.IDrawLotteryEngine
import com.jewdokimow.hiltdemo.lotteries.utilities.ILotteriesValidator
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.time.LocalDate


@RunWith(MockitoJUnitRunner::class)
class HiltSampleTest {

    @Mock
    lateinit var lotteriesAppData: LotteriesAppData

    @Mock
    lateinit var drawLotteryEngine: IDrawLotteryEngine<DailyLottery>

    @Mock
    lateinit var lotteriesValidator: ILotteriesValidator

    @Before
    fun initMocks() {
        `when`(lotteriesAppData.userLotteries)
            .thenReturn(
                listOf(
                    BirthdayLottery(
                        LocalDate.of(1993, 7, 27),
                        LocalDate.of(1993, 7, 27),
                        LocalDate.of(1993, 7, 27),
                        LocalDate.of(1993, 7, 27)
                    )
                )
            )
    }

    @Test
    fun sampleTest() {
        val repository = DailyLotteriesRepository(
            lotteriesAppData,
            drawLotteryEngine,
            lotteriesValidator
        )
        assertEquals(
            listOf<DailyLottery>(),
            repository.getLotteriesAvailableForUser()
        )

    }
}