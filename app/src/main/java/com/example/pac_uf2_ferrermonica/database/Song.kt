package com.example.pac_uf2_ferrermonica.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "song_table")
data class Song(
    @PrimaryKey var artista:String,
    @ColumnInfo(name = "cancion") val cancion: String,
    @ColumnInfo(name = "album") val album: String?,
)