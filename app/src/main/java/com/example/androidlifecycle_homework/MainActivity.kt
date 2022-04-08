package com.example.androidlifecycle_homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null){
            addSender()
        }
    }


    private fun addSender(){
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, SenderFragment())
            .commit()
    }
}