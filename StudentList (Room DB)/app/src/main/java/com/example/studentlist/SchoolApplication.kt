package com.example.studentlist

import android.app.Application
import android.util.Log

class SchoolApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        SchoolRepository.initialize(this)
    }

}