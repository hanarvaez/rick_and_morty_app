package co.com.monkeymobile.rick_and_morty_app.domain.repository

import androidx.paging.PagingData
import co.com.monkeymobile.rick_and_morty_app.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getPagedCharacters(): Flow<PagingData<Character>>
}
