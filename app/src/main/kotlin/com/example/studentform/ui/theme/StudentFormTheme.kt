package com.example.studentform.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val StudentFormColorScheme = darkColorScheme(
    primary = StudentFormColors.CoralAccent,
    onPrimary = StudentFormColors.NavyDeep,
    secondary = StudentFormColors.MintGlow,
    onSecondary = StudentFormColors.NavyDeep,
    background = StudentFormColors.NavyDeep,
    onBackground = StudentFormColors.TextOnDark,
    surface = StudentFormColors.NavyCard,
    onSurface = StudentFormColors.TextOnDark,
    outline = StudentFormColors.FieldBorder
)

@Composable
fun StudentFormTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = StudentFormColorScheme,
        typography = StudentFormTypography,
        content = content
    )
}
