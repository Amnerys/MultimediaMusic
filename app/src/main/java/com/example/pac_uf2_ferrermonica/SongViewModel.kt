package com.example.pac_uf2_ferrermonica

import androidx.lifecycle.*
import com.example.pac_uf2_ferrermonica.database.Song
import kotlinx.coroutines.launch

class SongViewModel(private val repository: SongRepository) : ViewModel() {

    // Con LiveData y allWords ponemos un observador para que se actualicen los datos solo si hay cambios
    // También nos aseguramos de que el repositorio esté separado de la UI con el ViewModel
    val allWords: LiveData<List<Song>> = repository.allSongs.asLiveData()

    /**
     * Lanzando una nueva Coroutine para insertar los datos de manera que no hayan bloqueos
     */
    fun insert(song: Song) = viewModelScope.launch {
        repository.insert(song)
    }

    fun deleteTable() = viewModelScope.launch {
        repository.clear()
    }
}

class SongViewModelFactory(private val repository: SongRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SongViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SongViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
