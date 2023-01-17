package com.jewdokimow.hiltdemo

import com.jewdokimow.hiltdemo.lotteries.LotteriesAppData
import com.jewdokimow.hiltdemo.lotteries.models.*
import com.jewdokimow.hiltdemo.lotteries.repositories.DailyLotteriesRepository
import com.jewdokimow.hiltdemo.lotteries.utilities.IDrawLotteryEngine
import com.jewdokimow.hiltdemo.lotteries.utilities.ILotteriesValidator
import com.jewdokimow.hiltdemo.lotteries.utilities.LotteriesValidator
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.time.LocalDate
import javax.inject.Inject


@RunWith(MockitoJUnitRunner::class)
class HiltSampleTest {

    @Mock
    lateinit var lotteriesAppData: LotteriesAppData

    @Mock
    lateinit var drawLotteryEngine: IDrawLotteryEngine<DailyLottery>

    @InjectMocks
    lateinit var lotteriesValidator: LotteriesValidator

    @Mock
    lateinit var timeEngine: TimeEngine

    @Before
    fun initMocks() {
        `when`(lotteriesAppData.userLotteries)
            .thenReturn(
                listOf(
                    DailyLottery(
                        LocalDate.of(1993, 7, 26),
                        LocalDate.of(1993, 7, 28),
                        LocalDate.of(1993, 7, 26),
                        LocalDate.of(1993, 7, 28)
                    )
                )
            )
        `when`(timeEngine.getCurrentTime())
            .thenReturn(
                LocalDate.of(1993, 7, 27)
            )
    }

    @Test
    fun sampleTest() {
        val repository = DailyLotteriesRepository(
            lotteriesAppData,
            drawLotteryEngine,
            lotteriesValidator,
            timeEngine
        )
        assertEquals(
            1,
            repository.getLotteriesAvailableForUser().size
        )

    }
}