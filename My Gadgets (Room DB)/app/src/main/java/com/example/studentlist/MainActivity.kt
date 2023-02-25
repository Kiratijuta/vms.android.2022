package com.example.studentlist

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentlist.Gadget
import com.example.studentlist.GadgetRepository
import com.example.studentlist.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_item.view.*
import java.util.UUID

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView.layoutManager = LinearLayoutManager(this)

//        SchoolRepository.get().deleteGadgets()

//        addGadgetToDatabase()
    }

    override fun onResume() {
        super.onResume()
        getGadgetFromDatabaseToListView()
    }

    public fun gotoAddNewGadget(v: View) {
        val intent = Intent(this, NewGadgetActivity::class.java)
        startActivity(intent)
    }

//    private fun addGadgetToDatabase() {
//        for (i in 1 until 5) {
//            val s = Gadget()
//            s.name = "STUDENT ${i}"
//            s.testResult = ((1..2).random() % 2) == 0
//
//            SchoolRepository.get().addGadget(s)
//        }
//    }

    private fun getGadgetFromDatabaseToListView() {
        GadgetRepository.get().getGadgets().observe(this, { gadgets ->
            gadgets.let {
                listView.adapter = GadgetAdapter(gadgets)

//                gadgets[0].testResult = true
//                SchoolRepository.get().updateGadget(gadgets[0])
            }
        })
    }

//    private fun sampleGadgets(): List<Gadget> {
//        val gadgets = mutableListOf<Gadget>()
//        for (i in 1 until 25) {
//            val s = Gadget()
//            s.name = "STUDENT ${i}"
//            s.testResult = ((1..2).random() % 2) == 0
//            gadgets += s
//        }
//        return  gadgets
//    }

    inner class GadgetHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        val gadgetName = itemView.gadgetName
        var gagetId = ""

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
//            Toast.makeText(p0!!.context, "${gadgetName.text} Clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(p0!!.context, EditGadgetActivity::class.java)
            intent.putExtra("ID", gagetId)
            intent.putExtra("NAME", gadgetName.text.toString())
            startActivity(intent)
        }
    }

    inner class GadgetAdapter(var gadgets: List<Gadget>): RecyclerView.Adapter<GadgetHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GadgetHolder {
            val view = layoutInflater.inflate(R.layout.row_item, parent, false)
            return GadgetHolder(view)
        }

        override fun onBindViewHolder(holder: GadgetHolder, position: Int) {
            holder.gagetId = gadgets[position].id.toString()
            holder.gadgetName.text = gadgets[position].name
        }

        override fun getItemCount(): Int {
            return gadgets.size
        }
    }

}