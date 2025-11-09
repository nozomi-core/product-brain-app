package app.productbrain

import app.productbrain.data.MultiService

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return MultiService("kdj").title
    }
}