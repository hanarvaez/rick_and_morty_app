package co.com.monkeymobile.rick_and_morty_app.data.source.remote

import co.com.monkeymobile.rick_and_morty_app.data.source.remote.response.CharactersPageResponse
import retrofit2.http.GET
import retrofit2.http.Query

object ApiUrl {

    const val REST_BASE_URL = "https://rickandmortyapi.com/api/"

    const val CHARACTERS_PAGE_PARTICLE = "character"
    const val CHARACTERS_PAGE_QUERY = "page"
}

interface ApiService {

    @GET(ApiUrl.CHARACTERS_PAGE_PARTICLE)
    suspend fun fetchCharactersPage(
        @Query(ApiUrl.CHARACTERS_PAGE_QUERY) pageNumber: Int = 1
    ): CharactersPageResponse
}
