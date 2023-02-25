package com.example.studentlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_new_gadget.*

class NewGadgetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_gadget)
    }

    public fun addNewGadget(v: View) {
        val g = Gadget()
        g.name = nameInput.text.toString()

        GadgetRepository.get().addGadget(g)
        finish()
    }

}