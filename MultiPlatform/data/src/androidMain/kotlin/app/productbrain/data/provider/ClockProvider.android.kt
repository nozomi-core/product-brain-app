package app.productbrain.data.provider

import app.productbrain.data.ClockInstant

actual class ClockProvider {
    actual fun now(): ClockInstant {
        return ClockInstant(System.currentTimeMillis())
    }
}