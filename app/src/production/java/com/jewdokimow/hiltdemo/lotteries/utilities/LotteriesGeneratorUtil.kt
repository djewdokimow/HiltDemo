package com.jewdokimow.hiltdemo.lotteries.utilities

import com.jewdokimow.hiltdemo.lotteries.LotteriesAppData
import com.jewdokimow.hiltdemo.lotteries.models.BaseLottery
import com.jewdokimow.hiltdemo.lotteries.models.BirthdayLottery
import com.jewdokimow.hiltdemo.lotteries.models.DailyLottery
import java.time.LocalDate

class LotteriesGeneratorUtil(private val data: LotteriesAppData) : ILotteriesGeneratorUtil {

    override fun shouldGenerateNewTicketsForUser(): Boolean {
        return data.lastGeneratedDate.plusDays(1L).isBefore(LocalDate.now())
    }

    override fun generateUserTickets() {
        val nowDate = LocalDate.now()
        val shouldGenerateBirthdayLottery = data.userData.birthday.let {
            it.dayOfMonth == nowDate.dayOfMonth && it.month == nowDate.month
        }
        this.data.userLotteries = listOfNotNull(
            if (shouldGenerateBirthdayLottery) {
                BirthdayLottery(
                    nowDate.minusDays(1),
                    nowDate.plusDays(1),
                    nowDate,
                    nowDate.plusDays(1)
                )
            } else {
                null
            },
            DailyLottery(nowDate.minusDays(1), nowDate.plusDays(1), nowDate, nowDate.plusDays(1)),
            DailyLottery(nowDate, nowDate.plusDays(2), nowDate.plusDays(1), nowDate.plusDays(2)),
        )
    }

}