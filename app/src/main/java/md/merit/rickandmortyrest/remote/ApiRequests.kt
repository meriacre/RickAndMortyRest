package md.merit.rickandmortyrest.remote

import md.merit.rickandmortyrest.models.characters.CharacterModel
import md.merit.rickandmortyrest.models.episodes.EpisodeModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequests {

    @GET("character/")
    suspend fun getCharacterList(@Query("page") page: Int): Response<CharacterModel>

    @GET("episode/")
    suspend fun getEpisodesList(@Query("page") page: Int): Response<EpisodeModel>
}