package and5.abrar.challenge_ch8.viewmodel

import and5.abrar.challenge_ch8.model.RespondAnimeItem
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(private val api : AnimeRepository):ViewModel() {
    private val animeState = MutableStateFlow(emptyList<RespondAnimeItem>())
    val dataState : StateFlow<List<RespondAnimeItem>>
    get() = animeState
    init {
        viewModelScope.launch {
            val anime = api.getAnime()
            animeState.value = anime
        }
    }
}