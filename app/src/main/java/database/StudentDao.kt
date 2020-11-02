package database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example1.project.Student
import java.util.*

@Dao
interface StudentDao{
    @Query("SELECT * FROM student")
    fun getStudents(): List<Student>

    @Query("SELECT * FROM student WHERE id=(:id)")
    fun getStudent(id: UUID): Student?
    @Insert
    fun addStudent(student: Student)
    @Update
    fun updateStudent(student: Student)

}