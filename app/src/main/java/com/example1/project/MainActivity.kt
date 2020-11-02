package com.example1.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val currentFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, Fragment1.newInstance())
            .commit()

    }


}