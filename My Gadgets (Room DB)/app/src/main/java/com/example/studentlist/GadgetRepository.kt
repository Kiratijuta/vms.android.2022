package com.example.studentlist

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import database.GadgetDatabase
import java.util.UUID
import java.util.concurrent.Executors

class GadgetRepository private constructor(context: Context) {

    private val database: GadgetDatabase = Room.databaseBuilder(
        context.applicationContext,
        GadgetDatabase::class.java,
        "gadget-database"
    ).build()

    private val gadgetDao = database.gadgetDao()
    private val executor = Executors.newSingleThreadExecutor()

    fun getGadgets(): LiveData<List<Gadget>> = gadgetDao.getGadgets()
    fun getGadget(id: UUID): LiveData<Gadget> = gadgetDao.getGadget(id)

    fun deleteGadgets() {
        executor.execute {
            gadgetDao.deleteGadgets()
        }
    }

    fun deleteGadget(id: UUID) {
        executor.execute {
            gadgetDao.deleteGadget(id)
        }
    }

    fun addGadget(gadget: Gadget) {
        executor.execute {
            gadgetDao.insertGadget(gadget)
        }
    }

    fun updateGadget(gadget: Gadget) {
        executor.execute {
            gadgetDao.updateGadget(gadget)
        }
    }

    companion object {

        private var instance: GadgetRepository? = null

        fun initialize(context: Context) {
            if (instance == null) {
                instance = GadgetRepository(context)
            }
        }

        fun get(): GadgetRepository {
            return instance ?:
            throw java.lang.IllegalStateException("GadgetRepository must be initialized")
        }

    }

}