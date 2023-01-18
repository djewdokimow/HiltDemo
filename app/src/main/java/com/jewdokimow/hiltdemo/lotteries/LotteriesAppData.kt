package com.jewdokimow.hiltdemo.lotteries

import com.jewdokimow.hiltdemo.lotteries.models.BaseLottery
import com.jewdokimow.hiltdemo.lotteries.models.UserData
import java.time.LocalDate

data class LotteriesAppData(
    val userLotteries: MutableList<BaseLottery> = mutableListOf(),
    val lastGeneratedDate: LocalDate = LocalDate.of(2023, 1, 1),
    val userData: UserData
)