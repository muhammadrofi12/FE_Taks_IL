package com.example.fe_taks_il.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.fe_taks_il.R
import com.example.fe_taks_il.ui.components.SocialMediaButton
import com.example.fe_taks_il.ui.theme.*

@Composable
fun ProfileScreen() {
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo, Top App Bar
        Image(
            painter = painterResource(id = R.drawable.logo_rofz),
            contentDescription = "Rofz Movie Logo",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 20.dp)
                .height(40.dp),
            contentScale = ContentScale.Fit
        )

        Text(
            text = "Halaman About",
            style = PoppinsMedium12,
            textAlign = TextAlign.Center,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        )

        // Profile Section
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF5F5F5)
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Img
                Image(
                    painter = painterResource(id = R.drawable.logo_profil),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Nama
                Text(
                    text = "Muhammad Rofi'ul Arham",
                    style = PoppinsBold24,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )

                // Email
                Text(
                    text = "muhammadrofi062@gmail.com",
                    style = PoppinsMedium14,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Universitas
                Text(
                    text = "STT Terpadu Nurul Fikri",
                    style = PoppinsMedium12,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )

                // Jurusan
                Text(
                    text = "Teknik Informatika",
                    style = PoppinsMedium12,
                    color = GreenPrimary,
                    textAlign = TextAlign.Center
                )
            }
        }

        // Social Media
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                SocialMediaButton(
                    icon = R.drawable.ic_github,
                    text = "GitHub",
                    backgroundColor = Color.Black,
                    onClick = { uriHandler.openUri("https://github.com/muhammadrofi12") }
                )

                SocialMediaButton(
                    icon = R.drawable.ic_linkedin,
                    text = "LinkedIn",
                    backgroundColor = Color(0xFF0A66C2),
                    onClick = { uriHandler.openUri("https://www.linkedin.com/in/muhammad-rofi-ul-arham/") }
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                SocialMediaButton(
                    icon = R.drawable.ic_instagram,
                    text = "Instagram",
                    backgroundColor = Color(0xFFE1306C),
                    onClick = { uriHandler.openUri("https://www.instagram.com/rofiull___/") }
                )

                SocialMediaButton(
                    icon = R.drawable.ic_facebook,
                    text = "Facebook",
                    backgroundColor = Color(0xFF1877F2),
                    onClick = { uriHandler.openUri("https://www.facebook.com/muhammad.rofi.exe.11") }
                )
            }
        }

        Box {
            Text(
                text = "Data Movie by Web",
                style = PoppinsMedium16,
                fontStyle = FontStyle.Italic,
                textDecoration = TextDecoration.Underline,
                color = Error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 24.dp)
                    .clickable(
                        onClick = { uriHandler.openUri("https://movie-app-v1-rofz.vercel.app/") }
                    )
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    rememberNavController()
    ProfileScreen()
}