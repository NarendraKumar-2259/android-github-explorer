package io.github.narendrakumar2259.githubexplorer.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage

// UserDetailScreen.kt

@Composable
fun UserDetailScreen(username: String, navController: NavHostController)  {
    val viewModel: UserViewModel = hiltViewModel()
    val uiState = viewModel.userDetailsState.collectAsStateWithLifecycle().value

    LaunchedEffect(username) {
        viewModel.fetchUserDetails(username)
    }

    BackHandler {
        viewModel.resetState()
        navController.popBackStack()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0D1117))
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(16.dp)
    ) {
        // Top back button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                viewModel.resetState()
                navController.popBackStack()
            }) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color(0xFF58A6FF)
                )
            }
            Text(
                text = "Profile",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFE6EDF3)
            )
        }
        when (uiState) {
            is UiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = Color(0xFF58A6FF))
                }
            }
            is UiState.Success -> {
                val user = uiState.userDetails
                // Profile card
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF161B22), RoundedCornerShape(12.dp))
                        .border(0.5.dp, Color(0xFF30363D), RoundedCornerShape(12.dp))
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = user.avatarUrl,
                        contentDescription = "Avatar",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color(0xFF388BFD), CircleShape)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(user.name ?: username, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFFE6EDF3))
                    Text("@${user.login}", fontSize = 13.sp, color = Color(0xFF8B949E))
                    if (!user.bio.isNullOrEmpty()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(user.bio, fontSize = 13.sp, color = Color(0xFF8B949E), textAlign = TextAlign.Center)
                    }
                    if (!user.location.isNullOrEmpty()) {
                        Spacer(modifier = Modifier.height(6.dp))
                        Text("📍 ${user.location}", fontSize = 12.sp, color = Color(0xFF58A6FF))
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                // Stats row
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf(
                        "Repos" to user.publicRepos,
                        "Followers" to user.followers,
                        "Following" to user.following
                    ).forEach { (label, value) ->
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .background(Color(0xFF161B22), RoundedCornerShape(8.dp))
                                .border(0.5.dp, Color(0xFF30363D), RoundedCornerShape(8.dp))
                                .padding(vertical = 12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("$value", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFFE6EDF3))
                            Text(label, fontSize = 11.sp, color = Color(0xFF8B949E))
                        }
                    }
                }
            }
            is UiState.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(uiState.message, color = Color(0xFFF85149))
                }
            }
            else -> {}
        }
    }
}