package com.jatin.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StudentEntiity(
    @PrimaryKey(autoGenerate = true)
    var rollNo: Int = 0,
    @ColumnInfo
    var name: String ?= null,

)