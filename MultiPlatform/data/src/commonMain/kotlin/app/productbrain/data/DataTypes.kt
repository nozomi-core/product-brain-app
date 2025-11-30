package app.productbrain.data

import kotlinx.serialization.Serializable

data class ClockInstant(val utcMillis: Long) {

    companion object {
        val DEFAULT = ClockInstant(0)
    }
}

@Serializable
data class SampleJson(val title: String)