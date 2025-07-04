package co.com.monkeymobile.rick_and_morty_app.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterResponse(
    val id: Int?,
    val image: String?,
    val name: String?,
    val species: String?,
    val gender: String?,
    val status: String?
): Parcelable
