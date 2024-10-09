package com.vimalcvs.myapplication.views.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.vimalcvs.myapplication.data.Match
import com.vimalcvs.myapplication.data.MatchXX
import com.vimalcvs.myapplication.data.ModelMain
import com.vimalcvs.myapplication.data.Offer
import com.vimalcvs.myapplication.utils.BottomNavigation
import com.vimalcvs.myapplication.utils.ErrorScreen
import com.vimalcvs.myapplication.utils.LoadingScreen
import com.vimalcvs.myapplication.viewmodel.PostViewModel
import com.vimalcvs.myapplication.viewmodel.UiState

@Composable
fun HomeScreen(navController: NavHostController) {
    val viewModel: PostViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { MainTopBar() },
        bottomBar = { BottomNavigation(navController) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            MainList(
                uiState = uiState,
                onRetryClick = { viewModel.getPosts() }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar() {
    val colorStops = arrayOf(
        0.4f to Color(0xFF69B8ED), 0.8f to Color(0xFF6560E0)
    )
    TopAppBar(modifier = Modifier
        .background(brush = Brush.horizontalGradient(colorStops = colorStops))
        .fillMaxWidth()
        .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black,
            navigationIconContentColor = Color.Black,
            actionIconContentColor = Color.Black
        ),
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    Icons.Outlined.Menu, contentDescription = "More"
                )
            }
        },
        title = { Text(text = "Hi, Vimal") },
        actions = {
            BalanceIcon()
            IconButton(onClick = {}) {
                Icon(
                    Icons.Default.Notifications,
                    tint = Color(0xFFA454DD),
                    contentDescription = "Notifications"
                )
            }
        })
}


@Composable
fun BalanceIcon() {
    val colors = listOf(Color(0xFFFF4B63), Color(0xFFA454DD))
    Row(
        modifier = Modifier
            .height(30.dp)
            .clip(RoundedCornerShape(6.dp))
            .border(
                1.2.dp, brush = Brush.horizontalGradient(colors), shape = RoundedCornerShape(6.dp)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "₹50",
            modifier = Modifier.padding(horizontal = 5.dp),
            color = Color.Transparent,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                brush = Brush.horizontalGradient(colors)
            )
        )
        Icon(
            imageVector = Icons.Default.Lock,
            contentDescription = "Balance Lock",
            tint = colors[1],
            modifier = Modifier
                .size(22.dp)
                .padding(end = 5.dp)
        )
    }
}

@Composable
fun MainList(
    uiState: UiState,
    onRetryClick: () -> Unit
) {
    val colorStops = arrayOf(
        0.4f to Color(0xFF69B8ED), 0.8f to Color(0xFF6560E0)
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.horizontalGradient(colorStops = colorStops))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 150.dp)
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(color = Color.White)
        )
        when (uiState) {
            is UiState.Loading -> LoadingScreen()
            is UiState.Error -> ErrorScreen(message = uiState.message, onRetryClick = onRetryClick)
            is UiState.SuccessPost -> MainSectionList(model = uiState.posts)
        }
    }
}

@Composable
fun MainSectionList(
    model: ModelMain
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        item {
            FeaturedTournamentList(model = model.upcoming_matches.match_list)
            CurrentOffersList(model = model.current_offers.offer_list)
            UserMatchesList(model = model.user_matches.match_list)
        }
        item {
            Spacer(modifier = Modifier.padding(vertical = 12.dp))
            Text(
                "Upcoming Matches",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 2.dp),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                color = Color.Black
            )
        }

        UpcomingMatchesList(model = model.upcoming_matches.match_list)
    }
}

@Composable
fun FeaturedTournamentList(model: List<Match>) {
    Text(
        "My Matches",
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .fillMaxWidth(),
        color = Color.White,
        fontSize = 18.sp,
        textAlign = TextAlign.Center
    )
    SwipeViewPager(model = model)
}

@Composable
fun CurrentOffersList(model: List<Offer>) {
    Spacer(modifier = Modifier.padding(vertical = 6.dp))
    PosterHorizontalItem(post = model[0])
}


@Composable
fun UserMatchesList(model: List<MatchXX>) {
    Text(
        "Indian T20 League",
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(horizontal = 20.dp, vertical = 2.dp),
        textAlign = TextAlign.Center,
        fontSize = 18.sp,
        color = Color.Black
    )
    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(model) { items ->
            ListIndianT20League(
                posts = items.match
            )
        }
    }
}

fun LazyListScope.UpcomingMatchesList(model: List<Match>) {
    items(model) { items ->
        ListUpComingItem(post = items)
    }
}
