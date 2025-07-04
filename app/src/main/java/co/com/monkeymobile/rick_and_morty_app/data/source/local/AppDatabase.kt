package co.com.monkeymobile.rick_and_morty_app.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import co.com.monkeymobile.rick_and_morty_app.data.source.local.dao.CharacterDao
import co.com.monkeymobile.rick_and_morty_app.data.source.local.entities.CharacterEntity

@Database(
    entities = [CharacterEntity::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "rick_and_morty_local"
    }

    abstract fun characterDao(): CharacterDao
}
