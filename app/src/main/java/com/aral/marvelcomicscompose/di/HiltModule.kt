package com.aral.marvelcomicscompose.di

import android.content.Context
import androidx.room.Room
import com.aral.marvelcomicscompose.api.ApiService
import com.aral.marvelcomicscompose.api.MarvelApiRepo
import com.aral.marvelcomicscompose.modal.db.CharacterDao
import com.aral.marvelcomicscompose.modal.db.CollectionDb
import com.aral.marvelcomicscompose.modal.db.CollectionDbRepo
import com.aral.marvelcomicscompose.modal.db.CollectionDbRepoImpl
import com.aral.marvelcomicscompose.modal.db.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

/**
 * Created by AralBenli on 12.05.2023.
 */


@Module
@InstallIn(ViewModelComponent::class)
class HiltModule {

    @Provides
    fun provideApiRepo() = MarvelApiRepo(ApiService.api)

    @Provides
    fun provideCollectionDb(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, CollectionDb::class.java, Constants.DB).build()

    @Provides
    fun provideCharacterDao(collectionDb: CollectionDb) = collectionDb.characterDao()

    @Provides
    fun provideDbRepoImpl(characterDao: CharacterDao): CollectionDbRepo =
        CollectionDbRepoImpl(characterDao)


}