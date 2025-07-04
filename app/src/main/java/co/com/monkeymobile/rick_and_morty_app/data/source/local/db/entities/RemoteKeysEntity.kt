package co.com.monkeymobile.rick_and_morty_app.data.source.local.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val REMOTE_KEYS_TABLE_NAME = "remote_keys"

const val REMOTE_KEYS_TABLE_ID = "id"

@Entity(tableName = REMOTE_KEYS_TABLE_NAME)
data class RemoteKeysEntity(
    @PrimaryKey @ColumnInfo(name = REMOTE_KEYS_TABLE_ID) val id: Int,
    val prevKey: Int?,
    val nextKey: Int?
)