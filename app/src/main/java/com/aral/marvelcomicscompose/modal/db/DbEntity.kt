package com.aral.marvelcomicscompose.modal.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aral.marvelcomicscompose.comicsToString
import com.aral.marvelcomicscompose.modal.CharacterResult

/**
 * Created by AralBenli on 12.05.2023.
 */

@Entity(tableName = Constants.CHARACTER_TABLE)
data class DbEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val apiId: Int?,
    val name: String?,
    val thumbnail: String?,
    val comics: String?,
    val description: String?
) {
    companion object {
        fun fromCharacter(character: CharacterResult) =
            DbEntity(
                id = 0,
                apiId = character.id,
                name = character.name,
                thumbnail = character.thumbnail?.path + "." + character.thumbnail?.extension,
                comics = character.comics?.items?.mapNotNull { it.name }?.comicsToString()
                    ?: "no comics",
                description = character.description
            )
    }
}
