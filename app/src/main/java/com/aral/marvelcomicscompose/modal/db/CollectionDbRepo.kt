package com.aral.marvelcomicscompose.modal.db

import kotlinx.coroutines.flow.Flow

/**
 * Created by AralBenli on 12.05.2023.
 */
interface CollectionDbRepo {

    suspend fun getCharactersFromRepo(): Flow<List<DbEntity>>

    suspend fun getCharacterFromRepo(characterId : Int): Flow<DbEntity>

    suspend fun addCharacterToRepo(character : DbEntity)

    suspend fun updateCharacterInRepo(character: DbEntity)

    suspend fun deleteCharacterInRepo(character : DbEntity)

}