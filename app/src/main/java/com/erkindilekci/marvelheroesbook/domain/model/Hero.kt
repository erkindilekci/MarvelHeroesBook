package com.erkindilekci.marvelheroesbook.domain.model

data class Hero(
    val color: String,
    val details: List<Detail>,
    val id: Int,
    val name: String,
    val poster: String,
    val quote: String
)