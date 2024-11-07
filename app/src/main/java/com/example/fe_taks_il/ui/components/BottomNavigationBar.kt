package com.example.fe_taks_il.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.fe_taks_il.R
import com.example.fe_taks_il.ui.theme.GreenPrimary
import com.example.fe_taks_il.ui.theme.PoppinsMedium12
import com.example.fe_taks_il.ui.theme.PoppinsRegular12

data class Screen(val route: String, val title: String, val icon: Int)

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    // cek kondisi aktif
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination

    val screens = listOf(
        Screen("home", "Home", R.drawable.ic_home),
        Screen("category", "Category", R.drawable.ic_category),
        Screen("profile", "Profile", R.drawable.ic_profile)
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .graphicsLayer {
                shadowElevation = 30f
                shape = RectangleShape
                clip = true
                alpha = 1.0f
            }
            .drawBehind {
                drawIntoCanvas { canvas ->
                    val paint = Paint()
                    val frameworkPaint = paint.asFrameworkPaint()
                    frameworkPaint.color = android.graphics.Color.WHITE
                    frameworkPaint.setShadowLayer(
                        30.dp.toPx(),
                        0f,
                        -4.dp.toPx(),
                        android.graphics.Color.argb(100, 0, 0, 0)
                    )
                    canvas.drawRect(
                        0f,
                        -4.dp.toPx(),
                        size.width,
                        size.height,
                        paint
                    )
                }
            }
            .background(Color.White.copy(alpha = 0.95f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            screens.forEach { screen ->
                val isSelected = currentDestination?.hierarchy?.any {
                    it.route == screen.route
                } == true

                NavigationBarItem(
                    title = screen.title,
                    iconResId = screen.icon,
                    isSelected = isSelected,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun NavigationBarItem(
    title: String,
    iconResId: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp)
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = title,
            tint = if (isSelected) GreenPrimary else Color.Gray,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = title,
            style = if (isSelected) PoppinsMedium12 else PoppinsRegular12,
            color = if (isSelected) GreenPrimary else Color.Gray,
            textAlign = TextAlign.Center
        )
    }
}