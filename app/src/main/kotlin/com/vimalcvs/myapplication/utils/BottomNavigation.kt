package com.vimalcvs.myapplication.utils

import android.widget.Toast
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.vimalcvs.myapplication.R

@Composable
fun BottomNavigation(navController: NavController) {
    val context = LocalContext.current
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination

    NavigationBar(
        modifier = Modifier,
        containerColor = Color(0xFFFFFFFF),
        contentColor = Color(0xFF757575),
    ) {
        NavigationBarItem(icon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_home),
                contentDescription = "Home",
                modifier = Modifier.size(22.dp)
            )
        }, colors = NavigationBarItemDefaults.colors(
            indicatorColor = Color(0xFF3629B7).copy(0.1f)
        ), selected = currentDestination?.route == "fragmentHome", onClick = {
            Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show()
        })
        NavigationBarItem(icon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Category",
                modifier = Modifier.size(22.dp)
            )
        }, colors = NavigationBarItemDefaults.colors(
            indicatorColor = Color(0xFF3629B7).copy(0.1f)
        ), selected = currentDestination?.route == "fragmentCategory", onClick = {
            Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show()
        })

        NavigationBarItem(icon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_massage),
                contentDescription = "Favorites",
                modifier = Modifier.size(22.dp)
            )
        }, colors = NavigationBarItemDefaults.colors(
            indicatorColor = Color(0xFF3629B7).copy(0.1f)
        ), selected = currentDestination?.route == "fragmentFavorite", onClick = {
            Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show()
        })
        NavigationBarItem(icon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_settings),
                contentDescription = "Settings",
                modifier = Modifier.size(22.dp)
            )
        }, colors = NavigationBarItemDefaults.colors(
            indicatorColor = Color(0xFF3629B7).copy(0.1f)
        ), selected = currentDestination?.route == "fragmentSettings", onClick = {
            Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show()
        })
    }
}
