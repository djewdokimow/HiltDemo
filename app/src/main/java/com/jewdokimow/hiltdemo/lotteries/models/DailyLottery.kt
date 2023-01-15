package com.jewdokimow.hiltdemo.lotteries.models

import java.time.LocalDate

class DailyLottery(
    visibilityStartDate: LocalDate,
    visibilityEndDate: LocalDate,
    validityStartDate: LocalDate,
    validityEndDate: LocalDate
) : BaseLottery(
    visibilityStartDate,
    visibilityEndDate,
    validityStartDate,
    validityEndDate
) {
    override fun prepareLabel(): String {
        return "Los: $visibilityStartDate"
    }
}