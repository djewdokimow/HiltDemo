package com.jewdokimow.hiltdemo

import android.app.Application
import com.jewdokimow.hiltdemo.lotteries.utilities.ILotteriesGeneratorUtil
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var generator: ILotteriesGeneratorUtil

    override fun onCreate() {
        super.onCreate()
        generator.generateUserTickets()
    }
}