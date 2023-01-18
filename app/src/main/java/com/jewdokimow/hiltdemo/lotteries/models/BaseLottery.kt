package com.jewdokimow.hiltdemo.lotteries.models

import java.time.LocalDate


abstract class BaseLottery(
    open val visibilityStartDate: LocalDate,
    open val visibilityEndDate: LocalDate,
    open val validityStartDate: LocalDate,
    open val validityEndDate: LocalDate
) {
    abstract fun prepareLabel(): String
}