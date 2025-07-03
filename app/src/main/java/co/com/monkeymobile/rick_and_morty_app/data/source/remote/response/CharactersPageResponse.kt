package co.com.monkeymobile.rick_and_morty_app.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharactersPageResponse(
    val info: CharactersPageInfoResponse?,
    val results: List<CharacterResponse>?
): Parcelable
