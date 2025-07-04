package co.com.monkeymobile.rick_and_morty_app.domain.use_case

import androidx.paging.PagingData
import co.com.monkeymobile.rick_and_morty_app.domain.model.Character
import co.com.monkeymobile.rick_and_morty_app.domain.repository.CharacterRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class GetPagedCharactersFlowUseCase @Inject constructor(
    private val repository: CharacterRepository
) {

    suspend operator fun invoke(coroutineDispatcher: CoroutineDispatcher): Flow<Result<PagingData<Character>>> {
        return try {
            withContext(coroutineDispatcher) {
                repository.getPagedCharacters()
                    .map { Result.success(it) }
                    .catch { exception -> emit(Result.failure(exception)) }
                    .flowOn(coroutineDispatcher)
            }
        } catch (exception: Exception) {
            flowOf(Result.failure(exception))
        }
    }
}
