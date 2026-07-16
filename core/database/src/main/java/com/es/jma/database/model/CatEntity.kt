package com.es.jma.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.es.jma.model.Breed
import com.es.jma.model.CatInfo

@Entity(
    tableName = "cats",
)
data class CatEntity (
    @PrimaryKey
    val id: String,
    val name: String,
    val idBreed: String,
    @ColumnInfo(defaultValue = "")
    val description: String,
    val origen: String,
    @ColumnInfo(defaultValue = "")
    val url: String,
    @ColumnInfo(defaultValue = "")
    val temperament: String,
    val addedAt: Long = System.currentTimeMillis()
)