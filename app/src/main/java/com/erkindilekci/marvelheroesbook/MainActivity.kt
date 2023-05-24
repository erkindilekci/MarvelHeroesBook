package com.erkindilekci.marvelheroesbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.erkindilekci.marvelheroesbook.presentation.util.Navigation
import com.erkindilekci.marvelheroesbook.presentation.ui.theme.MarvelHeroesBookTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelHeroesBookTheme {
                Navigation()
            }
        }
    }
}