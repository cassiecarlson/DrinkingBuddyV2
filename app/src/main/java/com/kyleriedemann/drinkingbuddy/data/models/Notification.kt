package com.kyleriedemann.drinkingbuddy.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.*

/**
 * A notification shown to the user
 */
@Entity(tableName = "notifications")
data class Notification @JvmOverloads constructor(
    @ColumnInfo(name = "title") var title: String = "",
    @ColumnInfo(name = "message") var message: String = "",
    @ColumnInfo(name = "read") var read: Boolean = false,
    @ColumnInfo(name = "datetime") var time: Instant = Instant.now(),
    @PrimaryKey @ColumnInfo(name = "id") var id: String = UUID.randomUUID().toString()
)