package com.aral.marvelcomicscompose.modal.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Created by AralBenli on 12.05.2023.
 */
@Dao
interface CharacterDao {

    @Query("SELECT * FROM ${Constants.CHARACTER_TABLE} ORDER BY id ASC")
    fun getCharacters(): Flow<List<DbEntity>>

    @Query("SELECT * FROM ${Constants.CHARACTER_TABLE} WHERE id = :characterId")
    fun getCharacter(characterId : Int): Flow<DbEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addCharacter(character : DbEntity)

    @Update
    fun updateCharacter(character : DbEntity)

    @Delete
    fun deleteCharacter(character : DbEntity)
}