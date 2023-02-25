package com.example.studentlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_edit_gadget.*
import kotlinx.android.synthetic.main.activity_new_gadget.*
import kotlinx.android.synthetic.main.activity_new_gadget.nameInput
import kotlinx.android.synthetic.main.activity_new_gadget.view.*
import kotlinx.android.synthetic.main.row_item.*
import java.util.*

class EditGadgetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_gadget)

        val gadgetName = intent.getStringExtra("NAME")
        nameUpdate.setText(gadgetName)

    }

    public fun updateGadget(v: View) {
        val gadgetId = intent.getStringExtra("ID")
        val g = Gadget(UUID.fromString(gadgetId), nameUpdate.text.toString())

        GadgetRepository.get().updateGadget(g)
        finish()
    }

    public fun deleteGadget(v: View) {
        val gadgetId = intent.getStringExtra("ID")

        GadgetRepository.get().deleteGadget(UUID.fromString(gadgetId))
        finish()
    }
}