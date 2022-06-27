package and5.abrar.challenge_ch8.viewmodel

import and5.abrar.challenge_ch8.model.RespondAnimeItem
import and5.abrar.challenge_ch8.network.ApiService
import javax.inject.Inject

class AnimeRepository @Inject constructor(private val animeApi : ApiService) {
    suspend fun getAnime():List<RespondAnimeItem>{
        return animeApi.getAllAnime()
    }
}