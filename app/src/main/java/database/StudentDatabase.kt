package database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example1.project.Student


    @Database(entities = [ Student::class ], version=1)
    @TypeConverters(StudentTypeConverters::class)
    abstract class StudentDatabase : RoomDatabase() {
        abstract fun studentDao(): StudentDao

    }

