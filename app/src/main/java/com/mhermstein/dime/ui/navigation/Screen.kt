package com.mhermstein.dime.ui.navigation

sealed class Screen {
    object Home : Screen()
    object Camera : Screen()
    object Gps : Screen()
    object Ruler : Screen()
    object History : Screen()
    object Settings : Screen()
}
