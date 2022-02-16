package com.example.pac_uf2_ferrermonica

import androidx.annotation.WorkerThread
import com.example.pac_uf2_ferrermonica.database.Song
import com.example.pac_uf2_ferrermonica.database.SongDao
import kotlinx.coroutines.flow.Flow

class SongRepository(private val songDao: SongDao) {

    // Utilizando Room se harán las consultas en un hilo aparte
    // Flow notificará al observador cuando haya cambios en los datos.
    val allSongs: Flow<List<Song>> = songDao.getAll()

    // Por defecto, Room quita del hilo principal las consultas suspendidas. No habrá procesos largos en el hilo pricipal
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(song: Song) {
        songDao.insert(song)
    }

    suspend fun clear() {
        songDao.clear()
    }
}