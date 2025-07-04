package co.com.monkeymobile.rick_and_morty_app.data.source.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import co.com.monkeymobile.rick_and_morty_app.domain.model.Character

const val CHARACTERS_TABLE_NAME = "characters"

const val CHARACTERS_TABLE_ID = "id"

@Entity(tableName = CHARACTERS_TABLE_NAME)
data class CharacterEntity(
    @PrimaryKey @ColumnInfo(name = CHARACTERS_TABLE_ID) val id: Int,
    val image: String,
    val name: String,
    val species: String,
    val gender: String,
    val status: String,
    val timestamp: Long = System.currentTimeMillis()
) {

    fun toCharacter() = Character(
        id = id,
        image = image,
        name = name,
        species = species,
        gender = gender,
        status = status
    )
}
