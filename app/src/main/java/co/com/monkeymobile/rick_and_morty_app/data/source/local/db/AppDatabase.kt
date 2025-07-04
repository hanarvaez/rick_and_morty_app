package co.com.monkeymobile.rick_and_morty_app.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import co.com.monkeymobile.rick_and_morty_app.data.source.local.db.dao.CharacterDao
import co.com.monkeymobile.rick_and_morty_app.data.source.local.db.dao.RemoteKeysDao
import co.com.monkeymobile.rick_and_morty_app.data.source.local.db.entities.CharacterEntity
import co.com.monkeymobile.rick_and_morty_app.data.source.local.db.entities.RemoteKeysEntity

@Database(
    entities = [CharacterEntity::class, RemoteKeysEntity::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "rick_and_morty_local"
    }

    abstract fun characterDao(): CharacterDao

    abstract fun remoteKeysDao(): RemoteKeysDao
}
