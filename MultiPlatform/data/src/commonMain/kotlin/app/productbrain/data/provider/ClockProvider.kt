package app.productbrain.data.provider

import app.productbrain.data.ClockInstant

expect class ClockProvider() {
    fun now(): ClockInstant
}

