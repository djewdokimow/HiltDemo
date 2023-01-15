package com.jewdokimow.hiltdemo.lotteries.models

import java.time.LocalDate


abstract class BaseLottery(
    val visibilityStartDate: LocalDate,
    val visibilityEndDate: LocalDate,
    val validityStartDate: LocalDate,
    val validityEndDate: LocalDate
){
    abstract fun prepareLabel() : String
}