package co.com.monkeymobile.rick_and_morty_app.data.source.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.com.monkeymobile.rick_and_morty_app.data.source.local.db.entities.REMOTE_KEYS_TABLE_ID
import co.com.monkeymobile.rick_and_morty_app.data.source.local.db.entities.REMOTE_KEYS_TABLE_NAME
import co.com.monkeymobile.rick_and_morty_app.data.source.local.db.entities.RemoteKeysEntity

@Dao
interface RemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg remoteKeys: RemoteKeysEntity)

    @Query("SELECT * FROM $REMOTE_KEYS_TABLE_NAME WHERE $REMOTE_KEYS_TABLE_ID = :id")
    suspend fun getRemoteKeysById(id: Int): RemoteKeysEntity?

    @Query("DELETE FROM $REMOTE_KEYS_TABLE_NAME")
    suspend fun clearRemoteKeys()
}
