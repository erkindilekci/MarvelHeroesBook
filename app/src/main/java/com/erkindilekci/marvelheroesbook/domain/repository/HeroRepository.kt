package com.erkindilekci.marvelheroesbook.domain.repository

import com.erkindilekci.marvelheroesbook.domain.model.Hero

interface HeroRepository {

    suspend fun getHeroes(): List<Hero>

    suspend fun getHero(id: Int): Hero
}