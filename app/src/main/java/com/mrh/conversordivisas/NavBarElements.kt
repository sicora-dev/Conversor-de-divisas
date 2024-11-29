package com.mrh.conversordivisas

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

enum class NavBarElements (
    val title: String,
    val icon: ImageVector,
    val route: String
) {

    INICIO(
        title = "Inicio",
        icon = Icons.Filled.Home,
        route = "home_view"
    ),
    CONVERSIONES(
        title = "Conversiones",
        icon = Icons.Filled.ShoppingCart,
        route = "conversiones_view"
    )


}