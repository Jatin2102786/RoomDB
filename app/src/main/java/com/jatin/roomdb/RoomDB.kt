package com.jatin.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// creating a room database class.
// Giving version to database annotation it's version and entity
@Database(version = 1, entities = [StudentEntiity::class])
abstract class RoomDB(): RoomDatabase()
{
    abstract  fun StudentDao(): StudentDao
    // Creating room database
    companion object
    {
        private var roomDB: RoomDB ?= null
        fun createDatabase(context: Context): RoomDB? {
            if (roomDB == null)
            {
                roomDB = Room.databaseBuilder(
                    context,
                    RoomDB::class.java,
                    context.resources.getString(R.string.app_name)
                ).build()
            }
            return roomDB!!
        }
    }
}