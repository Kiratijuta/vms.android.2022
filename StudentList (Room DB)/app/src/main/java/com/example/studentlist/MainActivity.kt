package com.example.studentlist

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_item.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView.layoutManager = LinearLayoutManager(this)

//        SchoolRepository.get().deleteStudents()

//        addStudentToDatabase()
        getStudentFromDatabaseToListView()
    }

    private fun addStudentToDatabase() {
        for (i in 1 until 5) {
            val s = Student()
            s.name = "STUDENT ${i}"
            s.testResult = ((1..2).random() % 2) == 0

            SchoolRepository.get().addStudent(s)
        }
    }

    private fun getStudentFromDatabaseToListView() {
        SchoolRepository.get().getStudents().observe(this, { students ->
            students.let {
                listView.adapter = StudentAdapter(students)

//                students[0].testResult = true
//                SchoolRepository.get().updateStudent(students[0])
            }
        })
    }

    private fun sampleStudents(): List<Student> {
        val students = mutableListOf<Student>()
        for (i in 1 until 25) {
            val s = Student()
            s.name = "STUDENT ${i}"
            s.testResult = ((1..2).random() % 2) == 0
            students += s
        }
        return  students
    }

    inner class StudentHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        var studentId = itemView.studentId
        val studentName = itemView.studentName
        val testResult = itemView.testResult

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            Toast.makeText(p0!!.context, "${studentName.text} Clicked", Toast.LENGTH_SHORT).show()
        }
    }

    inner class StudentAdapter(var students: List<Student>): RecyclerView.Adapter<StudentHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
            val view = layoutInflater.inflate(R.layout.row_item, parent, false)
            return StudentHolder(view)
        }

        override fun onBindViewHolder(holder: StudentHolder, position: Int) {
            holder.studentId.text = students[position].id.toString()
            holder.studentName.text = students[position].name
            holder.testResult.text = if (students[position].testResult) "Pass" else "Fail"
            holder.testResult.setTextColor(
                if (students[position].testResult) Color.parseColor("#00FF00")
                else Color.parseColor("#FF0000")
            )
        }

        override fun getItemCount(): Int {
            return students.size
        }
    }

}