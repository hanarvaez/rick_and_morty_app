package co.com.monkeymobile.rick_and_morty_app.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import co.com.monkeymobile.rick_and_morty_app.data.source.local.AppDatabase
import co.com.monkeymobile.rick_and_morty_app.data.source.local.entities.CharacterEntity
import co.com.monkeymobile.rick_and_morty_app.data.source.remote.ApiService
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.math.ceil

@OptIn(ExperimentalPagingApi::class)
class CharactersRemoteMediator @Inject constructor(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) : RemoteMediator<Int, CharacterEntity>() {

    private val characterDao = appDatabase.characterDao()

    override suspend fun initialize(): InitializeAction {
        val cacheTimeoutInMinutes = 5L

        val cacheTimeoutInMillis = TimeUnit.MINUTES.toMillis(cacheTimeoutInMinutes)

        val lastUpdate = characterDao.getLastTimestamp() ?: 0L

        return if (System.currentTimeMillis() - lastUpdate >= cacheTimeoutInMillis) {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        } else {
            InitializeAction.SKIP_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>
    ): MediatorResult {
        return try {
            val firstPage = 1

            val pageNumberToLoad = when (loadType) {
                LoadType.REFRESH -> {
                    // Always refresh from page 1
                    firstPage
                }

                LoadType.PREPEND -> {
                    // Don't load backwards
                    return MediatorResult.Success(endOfPaginationReached = true)
                }

                LoadType.APPEND -> {
                    // Get las item loaded to make next request
                    val lastItem = state.lastItemOrNull()

                    if (lastItem == null) {
                        // No items, start from page 1
                        firstPage
                    } else {
                        val currentCharactersCount = characterDao.getCount()

                        val currentPage = ceil(
                            currentCharactersCount.toFloat().div(state.config.pageSize.toFloat())
                        )

                        val nextPage = currentPage.toInt() + 1

                        nextPage
                    }
                }
            }

            // fetch characters from api
            val response = apiService.fetchCharactersPage(pageNumberToLoad)
            val items = response.results

            appDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    characterDao.clearAll()
                }

                val entities = items?.mapNotNull {
                    it.toCharacterEntity()
                }?.toTypedArray()

                entities?.let {
                    characterDao.insertAll(*it)
                }
            }

            // End of pagination
            MediatorResult.Success(
                endOfPaginationReached = response.info?.next == null
            )

        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}
