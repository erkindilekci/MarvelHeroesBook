package com.erkindilekci.marvelheroesbook.di

import com.erkindilekci.marvelheroesbook.data.remote.HeroApi
import com.erkindilekci.marvelheroesbook.data.repository.HeroRepositoryImpl
import com.erkindilekci.marvelheroesbook.domain.repository.HeroRepository
import com.erkindilekci.marvelheroesbook.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHeroApi(): HeroApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HeroApi::class.java)
    }

    @Provides
    @Singleton
    fun provideHeroRepository(api: HeroApi): HeroRepository {
        return HeroRepositoryImpl(api)
    }
}