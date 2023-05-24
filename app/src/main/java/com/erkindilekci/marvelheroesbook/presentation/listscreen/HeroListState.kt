package com.erkindilekci.marvelheroesbook.presentation.listscreen

import com.erkindilekci.marvelheroesbook.domain.model.Hero

data class HeroListState(
    val heroes: List<Hero> = emptyList(),
    val heroNames: List<String> = emptyList(),
    var isError: Boolean = false,
    var isLoading: Boolean = true
)
