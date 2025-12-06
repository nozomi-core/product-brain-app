package app.productbrain.platform

import app.productbrain.BuildConfig

actual fun isDebugBuild(): Boolean {
    return BuildConfig.DEBUG
}