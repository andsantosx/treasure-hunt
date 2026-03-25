package br.edu.satc.treasurehunt.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val PremiumPurple = Color(0xFF6C63FF)
val PremiumPink = Color(0xFFFF6584)
val DarkBackground = Color(0xFF121212)
val SurfaceColor = Color(0xFF1E1E1E)
val GlassWhite = Color(0x33FFFFFF)

private val DarkColorScheme = darkColorScheme(
    primary = PremiumPurple,
    secondary = PremiumPink,
    tertiary = Color(0xFF03DAC6),
    background = DarkBackground,
    surface = SurfaceColor,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = PremiumPurple,
    secondary = PremiumPink,
    tertiary = Color(0xFF018786),
    background = Color.White,
    surface = Color(0xFFF5F5F5),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black
)

@Composable
fun TreasureHuntTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(),
        content = content
    )
}
