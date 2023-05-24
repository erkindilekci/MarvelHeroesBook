package com.erkindilekci.marvelheroesbook.presentation.listscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.erkindilekci.marvelheroesbook.presentation.listscreen.components.HeroListItemCard
import com.erkindilekci.marvelheroesbook.presentation.listscreen.components.HeroListTopAppBar
import com.erkindilekci.marvelheroesbook.presentation.util.ErrorScreen
import com.erkindilekci.marvelheroesbook.presentation.util.LoadingScreen
import com.erkindilekci.marvelheroesbook.presentation.util.Screen
import com.erkindilekci.marvelheroesbook.presentation.ui.theme.BackgroundColor

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HeroListScreen(
    viewModel: HeroListViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state.value

    if (state.isLoading) {
        LoadingScreen()
    } else if (state.isError) {
        ErrorScreen()
    } else {
        Scaffold(
            topBar = {
                HeroListTopAppBar()
            },
            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                        .background(BackgroundColor),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Choose your hero",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(top = 32.dp, start = 76.dp),
                    )

                    state.heroes?.let { heroList ->
                        heroList.forEach { hero ->
                            val pagerState = rememberPagerState()
                            HorizontalPager(
                                pageCount = heroList.size,
                                state = pagerState,
                                pageSize = PageSize.Fixed(350.dp)
                            ) { index ->
                                val hero = heroList.get(index)
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(BackgroundColor)
                                ) {
                                    HeroListItemCard(
                                        hero = hero,
                                        onHeroClicked = { id ->
                                            navController.navigate("${Screen.DetailScreen.route}/$id")
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        )
    }
}