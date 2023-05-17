package com.aral.marvelcomicscompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aral.marvelcomicscompose.modal.CharacterResult
import com.aral.marvelcomicscompose.modal.db.CollectionDbRepo
import com.aral.marvelcomicscompose.modal.db.DbEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by AralBenli on 15.05.2023.
 */

@HiltViewModel
class CollectionDbViewModel @Inject constructor(
    private val repo: CollectionDbRepo
) : ViewModel() {

    val currentCharacter = MutableStateFlow<DbEntity?>(null)
    val collection = MutableStateFlow<List<DbEntity>>(listOf())

    init {
        getCollection()
    }

    private fun getCollection() {
        viewModelScope.launch {
            repo.getCharactersFromRepo().collect {
                collection.value = it
            }
        }
    }

    fun setCurrentCharacterId(characterId : Int?){
        characterId?.let {
            viewModelScope.launch(Dispatchers.IO) {
                repo.getCharacterFromRepo(it).collect {
                    currentCharacter.value = it
                }
            }
        }
    }

    fun addCharacter(character : CharacterResult){
        viewModelScope.launch(Dispatchers.IO) {
            repo.addCharacterToRepo(DbEntity.fromCharacter(character))
        }
    }

    fun deleteCharacter(character : DbEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteCharacterInRepo(character)
        }
    }
}