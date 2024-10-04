package com.example.funwithtime.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.funwithtime.HomeScreen
import com.example.funwithtime.SettingsScreen
import com.example.funwithtime.ToolsScreen



@Preview
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                listOfNavItems.forEach { navItem ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any {it.route == navItem.route} ==  true,
                        onClick = {
                            navController.navigate(navItem.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },

                        label = {Text(navItem.title)},
                        icon = {
                            BadgedBox(
                                badge =  {

                                    if (navItem.badgeCount != null) {
                                        Badge {
                                            Text(text = navItem.badgeCount.toString())
                                        }
                                    } else if(navItem.hasNews) {
                                        Badge()
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = if (currentDestination?.hierarchy?.any {it.route == navItem.route} ==  true) {
                                        navItem.selectedIcon
                                    } else navItem.unselectedIcon,
                                    contentDescription = navItem.title
                                )
                            }
                        }
                    )
                }
            }

        },

    ) { paddingValues ->
        NavHost(
            navController =  navController,
            startDestination = Screens.HomeScreen.name,
            modifier = Modifier
                .padding(paddingValues)
        ) {
           composable(route = Screens.HomeScreen.name ) {
               HomeScreen()
           }
           composable(route = Screens.ToolsScreen.name ) {
               ToolsScreen()
           }
           composable(route = Screens.SettingsScreen.name ) {
               SettingsScreen()
           }
        }
    }
}
