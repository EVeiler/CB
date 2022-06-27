package and5.abrar.challenge_ch8.network

import and5.abrar.challenge_ch8.model.RespondAnimeItem
import retrofit2.http.GET

interface ApiService {
    @GET("page/1")
    suspend fun getAllAnime():List<RespondAnimeItem>
}