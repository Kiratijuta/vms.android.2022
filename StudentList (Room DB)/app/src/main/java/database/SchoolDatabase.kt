package database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.studentlist.Student

@Database(entities = [Student::class], version = 1)
abstract class SchoolDatabase: RoomDatabase() {

    abstract fun studentDao(): StudentDAO

}