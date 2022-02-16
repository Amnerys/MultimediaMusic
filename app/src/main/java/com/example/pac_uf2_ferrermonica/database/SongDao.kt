package com.example.pac_uf2_ferrermonica.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface SongDao {
    @Query("SELECT * FROM song_table")
    fun getAll(): Flow<List<Song>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg song: Song)

    @Query("DELETE FROM song_table")
    suspend fun clear()
}