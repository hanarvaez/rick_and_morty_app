package co.com.monkeymobile.rick_and_morty_app.data.source.remote.response

import android.os.Parcelable
import co.com.monkeymobile.rick_and_morty_app.data.source.local.entities.CharacterEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterResponse(
    val id: Int?,
    val image: String?,
    val name: String?,
    val species: String?,
    val gender: String?,
    val status: String?
) : Parcelable {

    fun toCharacterEntity(): CharacterEntity? {
        if (
            id == null
            || image.isNullOrBlank()
            || name.isNullOrBlank()
            || species.isNullOrBlank()
            || gender.isNullOrBlank()
            || status.isNullOrBlank()
        ) {
            return null
        }

        return CharacterEntity(
            id = id,
            image = image,
            name = name,
            species = species,
            gender = gender,
            status = status
        )
    }
}
