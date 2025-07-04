package co.com.monkeymobile.rick_and_morty_app.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import co.com.monkeymobile.rick_and_morty_app.presentation.components.CharacterCard
import co.com.monkeymobile.rick_and_morty_app.presentation.components.ErrorRetryView
import co.com.monkeymobile.rick_and_morty_app.presentation.components.ErrorView
import co.com.monkeymobile.rick_and_morty_app.presentation.components.LoadingItemPlaceholder
import co.com.monkeymobile.rick_and_morty_app.presentation.components.LoadingMoreItemsIndicator
import co.com.monkeymobile.rick_and_morty_app.presentation.components.LoadingView
import co.com.monkeymobile.rick_and_morty_app.presentation.navigation.Screens
import co.com.monkeymobile.rick_and_morty_app.presentation.state.UiState
import co.com.monkeymobile.rick_and_morty_app.presentation.viewmodel.CharacterListViewModel

@Composable
fun CharacterListScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: CharacterListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val charactersPagingItems = viewModel.charactersPagingData.collectAsLazyPagingItems()

    when (uiState) {
        is UiState.Loading -> {
            LoadingView(modifier = Modifier.fillMaxSize())
        }

        is UiState.Error -> {
            val errorMessage = (uiState as UiState.Error).message

            ErrorView(
                message = errorMessage,
                onRetryClick = { charactersPagingItems.retry() },
                modifier = Modifier.fillMaxSize()
            )
        }

        UiState.Success -> {
            LazyColumn(modifier = modifier) {
                items(
                    count = charactersPagingItems.itemCount,
                    key = charactersPagingItems.itemKey { it.id },
                    contentType = charactersPagingItems.itemContentType { "Character" }
                ) { index ->
                    val character = charactersPagingItems[index]
                    if (character != null) {
                        CharacterCard(
                            character = character,
                            onItemClick = { character ->
                                navController.navigate(
                                    Screens.CharacterDetailScreen.createRoute(
                                        "${character.id}",
                                        character.image,
                                        character.name,
                                        character.species,
                                        character.gender,
                                        character.status
                                    )
                                )
                            })
                    } else {
                        LoadingItemPlaceholder()
                    }
                }

                charactersPagingItems.apply {
                    when {
                        loadState.refresh is LoadState.Loading && charactersPagingItems.itemCount == 0 -> {
                            item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                        }

                        loadState.append is LoadState.Loading -> {
                            item { LoadingMoreItemsIndicator(modifier = Modifier.fillMaxWidth()) }
                        }

                        loadState.refresh is LoadState.Error -> {
                            val e = loadState.refresh as LoadState.Error
                            item {
                                ErrorView(
                                    message = "Error when refreshing: ${e.error.localizedMessage}",
                                    onRetryClick = { charactersPagingItems.retry() }
                                )
                            }
                        }

                        loadState.append is LoadState.Error -> {
                            val e = loadState.append as LoadState.Error
                            item {
                                ErrorRetryView(
                                    message = "Error loading more items: ${e.error.localizedMessage}",
                                    onRetryClick = { charactersPagingItems.retry() }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
