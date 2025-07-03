package co.com.monkeymobile.rick_and_morty_app.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharactersPageInfoResponse(
    val count: Int?,
    val pages: Int?,
    val next: String?,
    val prev: String?
): Parcelable
