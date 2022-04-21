package com.xl.mycompose

import android.os.*
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.xl.mycompose.ui.theme.Purple500
import com.xl.mycompose.ui.theme.Purple700
import com.xl.mycompose.ui.theme.Teal100
import com.xl.mycompose.ui.theme.Teal200

class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
        if (!FcfrtAppBhUtils.isIgnoringBatteryOptimizations(this)) {
            FcfrtAppBhUtils.requestIgnoreBatteryOptimizations(this)
        }

    }
}

val title = mutableStateOf("")
val color = mutableStateOf(Teal100)
@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(topBar = { TitleBar() }, bottomBar = { BottomNavigationBar(navController) }) {
        Navigation(navController = navController)
    }
}


@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            title.value = "主页"
            color.value = Teal100
            HomeScreen()
        }
        composable(NavigationItem.Movies.route) {
            title.value = "发现"

            color.value = Purple500
            FindScreen()
        }
        composable(NavigationItem.Books.route) {
            title.value = "热门"
            color.value = Purple700
            HotScreen()
        }
        composable(NavigationItem.Profile.route) {
            title.value = "我的"

            color.value = Teal200
            ProfileScreen()
        }
    }
}


@Composable
fun TitleBar() {
    TopAppBar(
        title = { Text(text = title.value, fontSize = 18.sp) },
        backgroundColor = color.value,
        contentColor = Color.Black
    )
}

@RequiresApi(Build.VERSION_CODES.P)
@ExperimentalMaterialApi
@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Movies,
        NavigationItem.Books,
        NavigationItem.Profile
    )
    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Black
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    if (item.messageCount != 0) {
                        BadgeBox(backgroundColor = Color.Red,
                            contentColor = Color.White,
                            badgeContent = { Text(text = "8") }
                        ) {
                            Icon(
                                ImageVector.Companion.vectorResource(id = item.icon),
                                contentDescription = item.title
                            )
                        }
                    } else {
                        Icon(painterResource(id = item.icon), contentDescription = item.title)
                    }
                },
                label = { Text(text = item.title) },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Gray,
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }

    }
}