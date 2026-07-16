package com.es.jma.database.util

import androidx.room.TypeConverter
import java.time.Instant

@OptIn(kotlin.time.ExperimentalTime::class)
internal class InstantConverter {
    @TypeConverter
    fun longToInstant(value: Long?): Instant? =
        value?.let(Instant::ofEpochMilli)

    @TypeConverter
    fun instantToLong(instant: Instant?): Long? =
        instant?.toEpochMilli()
}