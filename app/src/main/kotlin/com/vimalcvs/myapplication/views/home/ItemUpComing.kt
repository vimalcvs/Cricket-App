package com.vimalcvs.myapplication.views.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.vimalcvs.myapplication.data.Match


@Composable
fun ListUpComingItem(post: Match) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 10.dp)
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
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .heightIn(min = 180.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopRow(model = post)
            CenterRow(model = post.teams)
            BottomRow(model = post)
        }
    }
}



