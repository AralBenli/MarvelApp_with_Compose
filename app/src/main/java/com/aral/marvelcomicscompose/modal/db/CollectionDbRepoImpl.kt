package com.aral.marvelcomicscompose.modal.db

import kotlinx.coroutines.flow.Flow

/**
 * Created by AralBenli on 12.05.2023.
 */
class CollectionDbRepoImpl(private val characterDao: CharacterDao) : CollectionDbRepo {
    override suspend fun getCharactersFromRepo(): Flow<List<DbEntity>> =
        characterDao.getCharacters()

    override suspend fun getCharacterFromRepo(characterId: Int): Flow<DbEntity> =
        characterDao.getCharacter(characterId)

    override suspend fun addCharacterToRepo(character: DbEntity) =
        characterDao.addCharacter(character)

    override suspend fun updateCharacterInRepo(character: DbEntity) =
        characterDao.updateCharacter(character)

    override suspend fun deleteCharacterInRepo(character: DbEntity) =
        characterDao.deleteCharacter(character)

}