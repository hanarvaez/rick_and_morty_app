package co.com.monkeymobile.rick_and_morty_app.presentation.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import co.com.monkeymobile.rick_and_morty_app.domain.model.Character
import co.com.monkeymobile.rick_and_morty_app.presentation.navigation.Screens
import co.com.monkeymobile.rick_and_morty_app.presentation.theme.Rick_and_morty_appTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            Rick_and_morty_appTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        AppNavigation(Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }
}

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.CharacterListScreen.route,
        modifier = modifier
    ) {
        composable(route = Screens.CharacterListScreen.route) {
            CharacterListScreen(
                navController = navController
            )
        }

        composable(
            route = Screens.CharacterDetailScreen.route,
            arguments = listOf(
                navArgument("characterId") { type = NavType.StringType },
                navArgument("image") {
                    type = NavType.StringType
                    nullable = false
                },
                navArgument("name") {
                    type = NavType.StringType
                    nullable = false
                },
                navArgument("species") {
                    type = NavType.StringType
                    nullable = false
                },
                navArgument("gender") {
                    type = NavType.StringType
                    nullable = false
                },
                navArgument("status") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { backStackEntry ->
            val customArguments = backStackEntry.arguments

            val characterId = customArguments?.getString("characterId")
            val image = customArguments?.getString("image")
            val name = customArguments?.getString("name")
            val species = customArguments?.getString("species")
            val gender = customArguments?.getString("gender")
            val status = customArguments?.getString("status")

            val character = Character(
                id = characterId?.toInt() ?: 0,
                image = image.orEmpty(),
                name = name.orEmpty(),
                species = species.orEmpty(),
                gender = gender.orEmpty(),
                status = status.orEmpty(),
            )

            if (characterId != null) {
                CharacterDetailScreen(
                    character = character,
                    navController = navController
                )
            } else {
                Text("Character Not found")
            }
        }
    }
}
