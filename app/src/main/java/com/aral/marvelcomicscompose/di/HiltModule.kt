package com.aral.marvelcomicscompose.di

import com.aral.marvelcomicscompose.api.ApiService
import com.aral.marvelcomicscompose.api.MarvelApiRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Created by AralBenli on 12.05.2023.
 */


@Module
@InstallIn(ViewModelComponent::class)
class HiltModule {

    @Provides
    fun provideApiRepo() = MarvelApiRepo(ApiService.api)



}