package com.junkfood.seal.ui.theme

import android.os.Build
import android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextDirection
import com.google.android.material.color.MaterialColors
import com.junkfood.seal.ui.common.LocalFixedColorRoles
import com.kyant.monet.LocalTonalPalettes
import com.kyant.monet.dynamicColorScheme

fun Color.applyOpacity(enabled: Boolean): Color {
    return if (enabled) this else this.copy(alpha = 0.62f)
}

@Composable
@ReadOnlyComposable
fun Color.harmonizeWith(other: Color) =
    Color(MaterialColors.harmonize(this.toArgb(), other.toArgb()))

@Composable
@ReadOnlyComposable
fun Color.harmonizeWithPrimary(): Color =
    this.harmonizeWith(other = MaterialTheme.colorScheme.primary)

@Composable
fun SealTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    isHighContrastModeEnabled: Boolean = false,
    content: @Composable () -> Unit,
) {
    val view = LocalView.current

    LaunchedEffect(darkTheme) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (darkTheme) {
                view.windowInsetsController?.setSystemBarsAppearance(
                    0,
                    APPEARANCE_LIGHT_STATUS_BARS,
                )
            } else {
                view.windowInsetsController?.setSystemBarsAppearance(
                    APPEARANCE_LIGHT_STATUS_BARS,
                    APPEARANCE_LIGHT_STATUS_BARS,
                )
            }
        }
    }

    val colorScheme =
        dynamicColorScheme(!darkTheme).run {
            if (darkTheme)
                copy(
                    primary = Color(0xFFFFCC00),
                    onPrimary = Color.Black,
                    primaryContainer = Color(0xFF3B3300),
                    onPrimaryContainer = Color(0xFFFFE57F),
                    secondary = Color(0xFFFFD54F),
                    onSecondary = Color.Black,
                    secondaryContainer = Color(0xFF2C2814),
                    onSecondaryContainer = Color(0xFFFFE57F),
                    surface = if (isHighContrastModeEnabled) Color.Black else Color(0xFF131316),
                    onSurface = Color(0xFFF0F0F2),
                    surfaceVariant = Color(0xFF1E1E24),
                    onSurfaceVariant = Color(0xFFA5A5AB),
                    background = if (isHighContrastModeEnabled) Color.Black else Color(0xFF0D0D10),
                    onBackground = Color(0xFFF0F0F2),
                    surfaceContainerLowest = Color(0xFF0A0A0C),
                    surfaceContainerLow = Color(0xFF121215),
                    surfaceContainer = Color(0xFF17171C),
                    surfaceContainerHigh = Color(0xFF1F1F24),
                    surfaceContainerHighest = Color(0xFF282830),
                )
            else this
        }

    val textStyle =
        LocalTextStyle.current.copy(
            lineBreak = LineBreak.Paragraph,
            textDirection = TextDirection.Content,
        )

    val tonalPalettes = LocalTonalPalettes.current

    CompositionLocalProvider(
        LocalFixedColorRoles provides FixedColorRoles.fromTonalPalettes(tonalPalettes),
        LocalTextStyle provides textStyle,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            shapes = Shapes,
            content = content,
        )
    }
}

@Composable
@Deprecated("Use SealTheme instead", replaceWith = ReplaceWith("SealTheme(content)"))
fun PreviewThemeLight(content: @Composable () -> Unit) {
    SealTheme(darkTheme = false, content = content)
}
