package org.landecs.notedecs.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import org.landecs.notedecs.data.dao.NoteDao
import org.landecs.notedecs.data.model.Note
import org.landecs.notedecs.data.model.Notebook
import org.landecs.notedecs.data.model.Tag

@Database(
    entities = [Note::class, Notebook::class, Tag::class],
    version = 1,
    exportSchema = false
)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: NotesDatabase? = null

        fun getInstance(context: Context): NotesDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDatabase::class.java,
                    "notes_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
