package com.jewdokimow.hiltdemo.lotteries.models

sealed class LotteryResult {
    object Failure : LotteryResult()
    object NewTicket : LotteryResult()
    class Success(val data: Reward) : LotteryResult()
}

data class Reward(
    val label: String
)