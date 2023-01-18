package com.jewdokimow.hiltdemo.lotteries.models

import java.time.LocalDate

data class BirthdayLottery(
    override val visibilityStartDate: LocalDate,
    override val visibilityEndDate: LocalDate,
    override val validityStartDate: LocalDate,
    override val validityEndDate: LocalDate
) : BaseLottery(
    visibilityStartDate,
    visibilityEndDate,
    validityStartDate,
    validityEndDate
) {
    override fun prepareLabel(): String {
        return "Los urodzinowy"
    }
}