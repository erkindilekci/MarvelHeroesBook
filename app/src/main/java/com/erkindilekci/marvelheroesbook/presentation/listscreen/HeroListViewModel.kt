package com.erkindilekci.marvelheroesbook.presentation.listscreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erkindilekci.marvelheroesbook.domain.repository.HeroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HeroListViewModel @Inject constructor(
    private val repository: HeroRepository
) : ViewModel() {
    private val _state = mutableStateOf(HeroListState())
    val state: State<HeroListState> = _state

    init {
        getHeroes()
    }

    private fun getHeroes() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val heroes = repository.getHeroes()
                _state.value = HeroListState(heroes = heroes)
                _state.value.isLoading = false
            } catch (e: IOException) {
                _state.value.isError = true
            } catch (e: HttpException) {
                _state.value.isError = true
            }
        }
    }
}