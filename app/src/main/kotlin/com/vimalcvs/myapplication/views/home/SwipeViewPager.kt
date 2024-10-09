package com.vimalcvs.myapplication.views.home

import android.widget.Toast
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.vimalcvs.myapplication.R
import com.vimalcvs.myapplication.data.Match
import com.vimalcvs.myapplication.data.Teams
import kotlinx.coroutines.delay


@OptIn(ExperimentalPagerApi::class)
@Composable
fun SwipeViewPager(model: List<Match>) {
    val context = LocalContext.current
    val pagerState = rememberPagerState(initialPage = model.size)
    LaunchedEffect(Unit) {
        if (model.isNotEmpty()) {
            while (true) {
                delay(2000)
                val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
                pagerState.scrollToPage(nextPage)
            }
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center,
        ) {
            if (model.isNotEmpty()) {
                HorizontalPager(
                    count = model.size,
                    state = pagerState,
                ) { pageIndex ->
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .shadow(
                                elevation = 5.dp,
                                shape = RoundedCornerShape(18.dp),
                                clip = true,
                                spotColor = Color.Blue.copy(alpha = 0.5f)
                            )
                            .background(Color.White)
                            .clickable {
                                Toast
                                    .makeText(context, "Coming Soon", Toast.LENGTH_SHORT)
                                    .show()
                            }
                            .fillMaxSize()
                            .heightIn(min = 180.dp),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TopRow(model = model[pageIndex])
                        CenterRow(model = model[pageIndex].teams)
                        BottomRow(model = model[pageIndex])

                    }
                }
            } else {
                Text(
                    text = "No matches available",
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center,
                    color = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.padding(vertical = 12.dp))
        PageIndicator(
            pageCount = model.size,
            currentPage = pagerState.currentPage,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}


@Composable
fun TopRow(model: Match) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                model.match_format,
                fontSize = 15.sp,
                color = Color(0xFF69B8ED),
                fontWeight = FontWeight.Bold,
            )

            VerticalDivider(
                color = Color.Gray,
                thickness = 2.dp,
                modifier = Modifier.size(14.dp)
            )

            Text(
                model.tournament_name,
                fontSize = 15.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Bold,
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.PlayArrow,
                contentDescription = "Play",
                tint = Color.Red,
                modifier = Modifier.size(20.dp)
            )
            Text(
                "Live",
                modifier = Modifier.padding(start = 2.dp, end = 16.dp),
                fontSize = 15.sp,
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
        }
    }
}


@Composable
fun CenterRow(model: Teams) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .width(80.dp)
                .height(50.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(android.graphics.Color.parseColor(model.a.logo_bg_color)),
                            Color.Transparent,
                        )
                    )
                ),
            contentAlignment = Alignment.CenterStart
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(model.a.logo_url)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.FillWidth,
                placeholder = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = stringResource(R.string.app_name),
                modifier = Modifier
                    .size(80.dp)
                    .offset(x = (30).dp)
            )
        }

        // Team A Code
        Text(
            model.a.code,
            modifier = Modifier.padding(horizontal = 4.dp),
            fontSize = 20.sp,
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )

        // VS Text
        Text(
            "VS",
            modifier = Modifier.padding(horizontal = 8.dp),
            fontSize = 16.sp,
            color = Color(0xFF69B8ED),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )

        // Team B Code
        Text(
            model.b.code,
            modifier = Modifier.padding(horizontal = 4.dp),
            fontSize = 20.sp,
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )

        Box(
            modifier = Modifier
                .width(80.dp)
                .height(50.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color(android.graphics.Color.parseColor(model.b.logo_bg_color))
                        )
                    )
                ),
            contentAlignment = Alignment.CenterEnd
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(model.b.logo_url)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.FillWidth,
                placeholder = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = stringResource(R.string.app_name),
                modifier = Modifier
                    .size(80.dp)
                    .offset(x = (-30).dp)
            )
        }
    }
}


@Composable
fun BottomRow(model: Match) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFFEDEDED)),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            model.name,
            modifier = Modifier.padding(10.dp),
            fontSize = 20.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
    }
}


@Composable
fun PageIndicator(pageCount: Int, currentPage: Int, modifier: Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        repeat(pageCount) {
            val gradientBrush = Brush.horizontalGradient(
                colors = listOf(
                    Color(0xFFFF4B63),
                    Color(0xFFA454DD)
                )
            )
            val size = animateDpAsState(
                targetValue = if (it == currentPage) 10.dp else 8.dp,
                label = ""
            )
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .size(size.value)
                    .clip(CircleShape)
                    .then(
                        if (it == currentPage) {
                            Modifier.background(gradientBrush)
                        } else {
                            Modifier.background(Color(0xFFD9D9D9))
                        }
                    )
            )
        }
    }
}

