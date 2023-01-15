package com.jewdokimow.hiltdemo.lotteries.models

import java.time.LocalDate

class BirthdayLottery(
    visibilityStartEndTs: LocalDate,
    visibilityDateEndTs: LocalDate,
    validityDateStartTs: LocalDate,
    validityDateEndTs: LocalDate
) : BaseLottery(
    visibilityStartEndTs,
    visibilityDateEndTs,
    validityDateStartTs,
    validityDateEndTs
) {
    override fun prepareLabel(): String {
        return "Los urodzinowy"
    }
}