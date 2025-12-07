package app.productbrain.design.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf

val LocalSpacing = staticCompositionLocalOf {
    ProductSpacing()
}

object ProductTheme {
    val spacing: ProductSpacing
        @Composable
        get() = LocalSpacing.current

}