package com.jatin.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface StudentDao
{
    @Insert
    fun insertStudentData(studentEntiity: StudentEntiity): Long

    @Query("SELECT * FROM studententiity")
    fun getStudentDAta(): List<StudentEntiity>

   @Delete
   fun deleteStudednt(studentEntiity: StudentEntiity)

   @Update
   fun updateStudent(studentEntiity: StudentEntiity)


}