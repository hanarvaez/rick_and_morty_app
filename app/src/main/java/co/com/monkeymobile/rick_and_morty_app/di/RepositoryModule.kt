package co.com.monkeymobile.rick_and_morty_app.di

import co.com.monkeymobile.rick_and_morty_app.data.repository.CharacterRepositoryImpl
import co.com.monkeymobile.rick_and_morty_app.domain.repository.CharacterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideCharacterRepository(
        repository: CharacterRepositoryImpl
    ): CharacterRepository
}
