package com.erkindilekci.marvelheroesbook.presentation.detailscreen

import com.erkindilekci.marvelheroesbook.domain.model.Hero

data class HeroDetailState(
    val hero: Hero? = null,
    var isError: Boolean = false,
    var isLoading: Boolean = true,
)