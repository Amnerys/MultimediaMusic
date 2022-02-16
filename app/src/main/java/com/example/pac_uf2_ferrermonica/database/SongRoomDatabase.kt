package com.example.pac_uf2_ferrermonica.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Song::class), version = 1, exportSchema = false)
abstract class SongRoomDatabase : RoomDatabase() {

    abstract fun songDao(): SongDao

    private class SongDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var songDao = database.songDao()

                    // Delete all content here.
                    songDao.clear()

                    // Add sample words.
                    var word = Song("Hello", "Hello2", "Hello3")
                    songDao.insert(word)
                    word = Song("World!", "World2", "World3")
                    songDao.insert(word)

                    // TODO: Add your own words!
                    word = Song("TODO!", "TODO1", "TODO2")
                    songDao.insert(word)
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: SongRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): SongRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SongRoomDatabase::class.java,
                    "song_database"
                )
                    .addCallback(SongDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}