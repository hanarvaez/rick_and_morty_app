package co.com.monkeymobile.rick_and_morty_app.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import co.com.monkeymobile.rick_and_morty_app.data.paging.CharactersRemoteMediator
import co.com.monkeymobile.rick_and_morty_app.data.source.local.AppDatabase
import co.com.monkeymobile.rick_and_morty_app.data.source.local.entities.CharacterEntity
import co.com.monkeymobile.rick_and_morty_app.domain.model.Character
import co.com.monkeymobile.rick_and_morty_app.domain.repository.CharacterRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class CharacterRepositoryImpl @Inject constructor(
    private val appDatabase: AppDatabase,
    private val mediator: CharactersRemoteMediator
) : CharacterRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getPagedCharacters(): Flow<PagingData<Character>> {
        val pageSize = 20

        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = false
            ),
            remoteMediator = mediator,
            pagingSourceFactory = { appDatabase.characterDao().getPagedCharacters() }
        ).flow.map { pagingData: PagingData<CharacterEntity> ->
            pagingData.map { it.toCharacter() }
        }
    }
}
