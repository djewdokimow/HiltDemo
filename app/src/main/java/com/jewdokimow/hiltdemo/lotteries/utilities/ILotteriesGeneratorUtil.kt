package com.jewdokimow.hiltdemo.lotteries.utilities

interface ILotteriesGeneratorUtil {
    fun shouldGenerateNewTicketsForUser(): Boolean
    fun generateUserTickets()
}