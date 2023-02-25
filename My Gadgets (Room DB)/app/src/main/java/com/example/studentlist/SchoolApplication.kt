package com.example.studentlist

import android.app.Application
import android.util.Log

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        GadgetRepository.initialize(this)
    }

}