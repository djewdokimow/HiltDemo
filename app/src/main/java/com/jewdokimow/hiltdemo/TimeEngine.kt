package com.jewdokimow.hiltdemo

import java.time.LocalDate

class TimeEngine {
    fun getCurrentTime(): LocalDate {
        return LocalDate.now()
    }
}