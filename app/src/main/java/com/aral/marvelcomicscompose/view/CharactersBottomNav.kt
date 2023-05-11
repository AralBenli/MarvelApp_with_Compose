package com.aral.marvelcomicscompose.view

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.aral.marvelcomicscompose.Destination
import com.aral.marvelcomicscompose.R

/**
 * Created by AralBenli on 11.05.2023.
 */


@Composable
fun CharactersBottomNav(navController: NavHostController) {
    BottomNavigation(elevation = 5.dp) {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry.value?.destination
        val iconLibrary = painterResource(id = R.drawable.ic_library_100)
        val iconCollection = painterResource(id = R.drawable.ic_collection_100)

        BottomNavigationItem(
            selected = currentDestination?.route == Destination.Library.route,
            onClick = {
                navController.navigate(Destination.Library.route) {
                    popUpTo(Destination.Library.route)
                    launchSingleTop = true
                }
            },
            icon = { Icon(
                painter = iconLibrary,
                contentDescription = null,
                tint = Color.Unspecified) },
            label = { Text(text = "Library") }
        )

        BottomNavigationItem(
            selected = currentDestination?.route == Destination.Collection.route,
            onClick = {
                navController.navigate(Destination.Collection.route) {
                    launchSingleTop = true
                }
            },
            icon = { Icon(
                painter = iconCollection,
                contentDescription = null,
                tint = Color.Unspecified) },
            label = { Text(text = "Collection") }
        )
    }
}