package co.com.monkeymobile.rick_and_morty_app.data.source.local.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.com.monkeymobile.rick_and_morty_app.data.source.local.db.entities.CHARACTERS_TABLE_ID
import co.com.monkeymobile.rick_and_morty_app.data.source.local.db.entities.CHARACTERS_TABLE_NAME
import co.com.monkeymobile.rick_and_morty_app.data.source.local.db.entities.CharacterEntity

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg entity: CharacterEntity)

    @Query("DELETE FROM $CHARACTERS_TABLE_NAME")
    suspend fun clearItems()

    @Query("SELECT * FROM $CHARACTERS_TABLE_NAME ORDER BY $CHARACTERS_TABLE_ID ASC")
    fun getPagedCharacters(): PagingSource<Int, CharacterEntity>
}
