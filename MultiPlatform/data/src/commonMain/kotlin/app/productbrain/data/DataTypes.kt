package app.productbrain.data

import kotlinx.serialization.Serializable

data class ClockInstant(val utcTimeMillis: Long)

@Serializable
data class SampleJson(val title: String)