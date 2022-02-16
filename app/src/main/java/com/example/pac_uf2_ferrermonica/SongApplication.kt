package com.example.pac_uf2_ferrermonica

import android.app.Application
import com.example.pac_uf2_ferrermonica.database.SongRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class SongApplication : Application() {
    // Este scope se destruirá tras el proceso, no hace falta cancelarlo
    val applicationScope = CoroutineScope(SupervisorJob())

    // Si usamos by lazy la BD y el repositorio se crean solo cuando se necesitan y no al iniciar la app
    val database by lazy { SongRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { SongRepository(database.songDao()) }
}