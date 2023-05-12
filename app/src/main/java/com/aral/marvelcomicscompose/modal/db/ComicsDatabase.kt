package com.aral.marvelcomicscompose.modal.db

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by AralBenli on 12.05.2023.
 */


@Database(entities = [DbEntity::class], version = 1, exportSchema = false)

abstract class CollectionDb : RoomDatabase() {
    abstract fun characterDao() : CharacterDao
}