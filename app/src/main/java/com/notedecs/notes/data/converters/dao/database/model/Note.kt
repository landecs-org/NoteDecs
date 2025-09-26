package org.landecs.notedecs.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import org.landecs.notedecs.data.converters.DateConverter
import org.landecs.notedecs.data.converters.StringListConverter
import java.util.Date
import java.util.UUID

@Entity(tableName = "notes")
@TypeConverters(DateConverter::class, StringListConverter::class)
data class Note(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val title: String = "",
    val content: String = "",
    val createdAt: Date = Date(),
    val updatedAt: Date = Date(),
    val isPinned: Boolean = false,
    val isArchived: Boolean = false,
    val isTrashed: Boolean = false,
    val notebookId: String? = null,
    val tags: List<String> = emptyList(),
    val isEncrypted: Boolean = false,
    val reminderAt: Date? = null,
    val color: Int? = null,
    val attachmentUris: List<String> = emptyList()
) {
    val previewText: String
        get() = content.take(150)

    val wordCount: Int
        get() = content.split("\\s+".toRegex()).count { it.isNotBlank() }
}

@Entity(tableName = "notebooks")
data class Notebook(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val name: String,
    val color: Int,
    val order: Int = 0,
    val createdAt: Date = Date()
)

@Entity(tableName = "tags")
data class Tag(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val name: String,
    val color: Int? = null,
    val createdAt: Date = Date()
)
