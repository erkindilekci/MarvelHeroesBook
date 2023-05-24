package com.erkindilekci.marvelheroesbook.presentation.detailscreen

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erkindilekci.marvelheroesbook.domain.repository.HeroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class HeroDetailViewModel @Inject constructor(
    private val repository: HeroRepository
) : ViewModel() {
    private val _state = mutableStateOf(HeroDetailState())
    val state: State<HeroDetailState> = _state

    var isOpened by mutableStateOf(false)
        private set

    fun getHero(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val hero = repository.getHero(id)
                _state.value = HeroDetailState(hero = hero)
                _state.value.isLoading = false
            } catch (e: Exception) {
                _state.value.isLoading = false
                _state.value.isError = true
            } catch (e: HttpException) {
                _state.value.isLoading = false
                _state.value.isError = true
            }
        }
    }

    fun updateIsOpened(bool: Boolean) {
        isOpened = bool
    }
}
