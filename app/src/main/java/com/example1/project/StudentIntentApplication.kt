package com.example1.project

import android.app.Application

class StudentIntentApplication: Application(){
    override fun onCreate() {
        super.onCreate()
        StudentRepository.initialize(this)
    }
}
