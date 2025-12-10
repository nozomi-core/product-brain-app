package app.productbrain.common

import android.util.Log

actual object Forest {
    actual fun d(message: String) {
        Log.d("forest.log", message)
    }

    actual fun e(exception: Exception) {
        Log.e("ForestException", "Some exception has occured", exception)
    }
}