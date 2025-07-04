package co.com.monkeymobile.rick_and_morty_app.presentation.navigation

sealed class Screens(val route: String) {

    object CharacterListScreen : Screens(route = "character_list_screen")

    object CharacterDetailScreen : Screens(
        route = "character_detail_screen/{characterId}?image={image}&name={name}&species={species}&gender={gender}&status={status}"
    ) {
        fun createRoute(
            characterId: String,
            image: String,
            name: String,
            species: String,
            gender: String,
            status: String
        ) = "character_detail_screen/$characterId?image=$image&name=$name&species=$species&gender=$gender&status=$status"
    }
}
