package com.erkindilekci.marvelheroesbook.data.repository

import com.erkindilekci.marvelheroesbook.data.remote.HeroApi
import com.erkindilekci.marvelheroesbook.domain.model.Hero
import com.erkindilekci.marvelheroesbook.domain.repository.HeroRepository
import javax.inject.Inject

class HeroRepositoryImpl @Inject constructor(
    private val api: HeroApi
) : HeroRepository {

    override suspend fun getHeroes(): List<Hero> {
        return api.getHeroes()
    }

    override suspend fun getHero(id: Int): Hero {
        return api.getHeroes().filter { it.id == id }.first()
    }
}