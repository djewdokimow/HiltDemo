package com.jewdokimow.hiltdemo

import com.jewdokimow.hiltdemo.lotteries.LotteriesAppData
import com.jewdokimow.hiltdemo.lotteries.models.*
import com.jewdokimow.hiltdemo.lotteries.repositories.DailyLotteriesRepository
import com.jewdokimow.hiltdemo.lotteries.utilities.IDrawLotteryEngine
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


@RunWith(MockitoJUnitRunner::class)
class DailyLotteriesRepositoryTests {

    @Mock
    lateinit var lotteriesAppData: LotteriesAppData

    @Mock
    lateinit var drawLotteryEngine: IDrawLotteryEngine<DailyLottery>

    @InjectMocks
    lateinit var lotteriesValidator: LotteriesValidator

    @Mock
    lateinit var timeEngine: TimeEngine

    private lateinit var repository: DailyLotteriesRepository

    @Before
    fun initMocks() {
        `when`(lotteriesAppData.userLotteries)
            .thenReturn(
                mutableListOf(
                    BirthdayLottery(
                        LocalDate.of(1993, 7, 27),
                        LocalDate.of(1993, 7, 28),
                        LocalDate.of(1993, 7, 26),
                        LocalDate.of(1993, 7, 29)
                    ),
                    DailyLottery(
                        LocalDate.of(1993, 7, 29),
                        LocalDate.of(1993, 7, 30),
                        LocalDate.of(1993, 7, 27),
                        LocalDate.of(1993, 7, 30)
                    ),
                    DailyLottery(
                        LocalDate.of(1993, 7, 27),
                        LocalDate.of(1993, 7, 28),
                        LocalDate.of(1993, 7, 26),
                        LocalDate.of(1993, 7, 29)
                    )
                )
            )
        `when`(timeEngine.getCurrentTime())
            .thenReturn(
                LocalDate.of(1993, 7, 27)
            )
        repository = DailyLotteriesRepository(
            lotteriesAppData,
            drawLotteryEngine,
            lotteriesValidator,
            timeEngine
        )
    }

    @Test
    fun getLotteriesAvailableForUserTest() {
        assertEquals(
            1,
            repository.getLotteriesAvailableForUser().size
        )
    }

    @Test
    fun getLotteriesVisibleForUserTest() {
        assertEquals(
            2,
            repository.getLotteriesVisibleForUser().size
        )
    }

    @Test
    fun drawLotteryTest() {
        val lottery = repository.getLotteriesAvailableForUser().first()
        repository.drawLottery(lottery)
        assertEquals(
            emptyList<DailyLottery>(),
            repository.getLotteriesAvailableForUser()
        )
    }
}