package co.com.monkeymobile.rick_and_morty_app.data.source.remote

import co.com.monkeymobile.rick_and_morty_app.data.source.remote.response.CharactersPageResponse

interface CharactersPageRemoteDataSource {

    suspend fun fetchCharactersPage(pageNumber: Int): CharactersPageResponse

}
