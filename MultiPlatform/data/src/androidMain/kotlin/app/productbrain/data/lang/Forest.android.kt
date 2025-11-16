package app.productbrain.data.lang

import android.util.Log

actual object Forest {
    actual fun d(message: String) {
        Log.d("forest.log", message)
    }
}