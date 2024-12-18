package com.example.noteapp.util

import androidx.room.TypeConverter
import java.util.UUID

class UUIDConverter {
    @TypeConverter
    fun fromUUID(uuid: UUID): String? {
        return uuid.toString()
    }

    @TypeConverter
    fun uuidFromString(uuidString: String?): UUID? {
        return UUID.fromString(uuidString)
    }
}