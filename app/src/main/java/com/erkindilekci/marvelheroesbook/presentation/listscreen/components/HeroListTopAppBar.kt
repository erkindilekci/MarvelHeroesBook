package com.erkindilekci.marvelheroesbook.presentation.listscreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.erkindilekci.marvelheroesbook.R
import com.erkindilekci.marvelheroesbook.presentation.ui.theme.BackgroundColor

@Composable
fun HeroListTopAppBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(BackgroundColor),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.marvel_icon),
            contentDescription = "Marvel Studios",
            modifier = Modifier
                .width(160.dp)
                .padding(8.dp)
        )
    }
}