package co.com.monkeymobile.rick_and_morty_app.data.source.remote.impl

import co.com.monkeymobile.rick_and_morty_app.data.source.remote.ApiService
import co.com.monkeymobile.rick_and_morty_app.data.source.remote.CharactersPageRemoteDataSource
import co.com.monkeymobile.rick_and_morty_app.data.source.remote.response.CharactersPageResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharactersPageRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : CharactersPageRemoteDataSource {

    override suspend fun fetchCharactersPage(pageNumber: Int): CharactersPageResponse =
        apiService.fetchCharactersPage(pageNumber)
}
