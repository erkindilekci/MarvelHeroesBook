package com.erkindilekci.marvelheroesbook.data.remote

import com.erkindilekci.marvelheroesbook.domain.model.Hero
import retrofit2.http.GET

interface HeroApi {
    @GET("erkindil/Json/main/marvel.json")
    suspend fun getHeroes(): List<Hero>
}