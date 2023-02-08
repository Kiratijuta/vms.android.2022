package com.example.studentlist

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import database.SchoolDatabase
import java.util.UUID
import java.util.concurrent.Executors

class SchoolRepository private constructor(context: Context) {

    private val database: SchoolDatabase = Room.databaseBuilder(
        context.applicationContext,
        SchoolDatabase::class.java,
        "student-database"
    ).build()

    private val studentDao = database.studentDao()
    private val executor = Executors.newSingleThreadExecutor()

    fun getStudents(): LiveData<List<Student>> = studentDao.getStudents()
    fun getStudent(id: UUID): LiveData<Student> = studentDao.getStudent(id)

    fun addStudent(student: Student) {
        executor.execute {
            studentDao.insertStudent(student)
        }
    }

    fun updateStudent(student: Student) {
        executor.execute {
            studentDao.updateStudent(student)
        }
    }

    companion object {

        private var instance: SchoolRepository? = null

        fun initialize(context: Context) {
            if (instance == null) {
                instance = SchoolRepository(context)
            }
        }

        fun get(): SchoolRepository {
            return instance ?:
            throw java.lang.IllegalStateException("SchoolRepository must be initialized")
        }

    }

}