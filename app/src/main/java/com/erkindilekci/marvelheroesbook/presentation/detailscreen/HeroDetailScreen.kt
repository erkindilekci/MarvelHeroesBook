package com.erkindilekci.marvelheroesbook.presentation.detailscreen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.erkindilekci.marvelheroesbook.R
import com.erkindilekci.marvelheroesbook.domain.model.Detail
import com.erkindilekci.marvelheroesbook.presentation.detailscreen.components.HeroDetailBottomSheetContent
import com.erkindilekci.marvelheroesbook.presentation.detailscreen.components.HeroDetailItemCard
import com.erkindilekci.marvelheroesbook.presentation.util.ErrorScreen
import com.erkindilekci.marvelheroesbook.presentation.util.LoadingScreen
import com.erkindilekci.marvelheroesbook.presentation.ui.theme.BackgroundColor

@Composable
fun HeroDetailScreen(
    id: Int,
    viewModel: HeroDetailViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state.value

    LaunchedEffect(key1 = true) {
        viewModel.getHero(id)
    }

    if (state.isLoading) {
        LoadingScreen()
    } else if (state.isError) {
        ErrorScreen()
    } else {
        state.hero?.let { hero ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BackgroundColor)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.65f),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(hero.poster)
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(bottomEnd = 5.dp, bottomStart = 5.dp))
                            .shadow(5.dp),
                        contentScale = ContentScale.Crop,
                        placeholder = painterResource(id = R.drawable.loading_img)
                    )

                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(start = 32.dp, bottom = 48.dp, end = 24.dp)
                    ) {
                        Text(
                            text = hero.name,
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White,
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = hero.quote,
                            style = MaterialTheme.typography.headlineSmall,
                            fontSize = 20.sp,
                            color = Color.White,
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Series",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Medium,
                    fontSize = 26.sp,
                    color = Color.White,
                    modifier = Modifier.padding(start = 16.dp)
                )

                val isOpened = viewModel.isOpened
                var selectedDetail by remember { mutableStateOf<Detail?>(null) }

                LazyRow(modifier = Modifier.fillMaxWidth()) {
                    items(hero.details) { detail ->
                        HeroDetailItemCard(
                            detail = detail,
                            onPosterClicked = { clickedDetail ->
                                viewModel.updateIsOpened(true)
                                selectedDetail = clickedDetail
                            }
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                }

                if (isOpened) {
                    Dialog(
                        onDismissRequest = { viewModel.updateIsOpened(false) },
                        properties = DialogProperties(usePlatformDefaultWidth = false)
                    ) {
                        selectedDetail?.let { detail ->
                            HeroDetailBottomSheetContent(detail = detail)
                        }
                    }
                }
            }
        }
    }

    BackHandler {
        navController.navigateUp()
    }
}