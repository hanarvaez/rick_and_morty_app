package co.com.monkeymobile.rick_and_morty_app.domain.model

data class Character(
    val id: Int,
    val image: String,
    val name: String,
    val species: String,
    val gender: String,
    val status: String
)
