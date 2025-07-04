package co.com.monkeymobile.rick_and_morty_app.di

import android.content.Context
import androidx.room.Room
import co.com.monkeymobile.rick_and_morty_app.data.source.local.AppDatabase
import co.com.monkeymobile.rick_and_morty_app.data.source.local.AppDatabase.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room
        .databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        )
        .fallbackToDestructiveMigration(true)
        .build()
}
