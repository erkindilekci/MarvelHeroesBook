package com.erkindilekci.marvelheroesbook.presentation.listscreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.erkindilekci.marvelheroesbook.R
import com.erkindilekci.marvelheroesbook.domain.model.Hero

@Composable
fun HeroListItemCard(
    hero: Hero,
    onHeroClicked: (Int) -> Unit
) {
    val colorValue = hero.color.substring(2).toLong(16)
    val color = Color(colorValue.toInt())

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp, bottom = 32.dp, start = 32.dp, end = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = color
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onHeroClicked(hero.id) },
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(hero.poster)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.loading_img)
            )

            Text(
                text = hero.name,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 32.dp, bottom = 48.dp)
            )
        }
    }
}