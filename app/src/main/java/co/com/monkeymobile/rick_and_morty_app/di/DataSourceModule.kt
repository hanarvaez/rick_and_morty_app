package co.com.monkeymobile.rick_and_morty_app.di

import co.com.monkeymobile.rick_and_morty_app.data.source.remote.CharactersPageRemoteDataSource
import co.com.monkeymobile.rick_and_morty_app.data.source.remote.impl.CharactersPageRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindCharactersPageRemoteDataSource(
        source: CharactersPageRemoteDataSourceImpl
    ): CharactersPageRemoteDataSource
}
