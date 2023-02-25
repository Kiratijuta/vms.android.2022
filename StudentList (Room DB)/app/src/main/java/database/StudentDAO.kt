package database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.studentlist.Student
import java.util.UUID

@Dao
interface StudentDAO {

    @Query("SELECT * FROM student")
    fun getStudents(): LiveData<List<Student>>

    @Query("SELECT * FROM student WHERE id = (:id)")
    fun getStudent(id: UUID): LiveData<Student>

    @Insert
    fun insertStudent(s: Student)

    @Update
    fun updateStudent(s: Student)

    @Query("DELETE FROM student")
    fun deleteStudents()


}