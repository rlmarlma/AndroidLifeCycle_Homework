package com.example.androidlifecycle_homework

import androidx.lifecycle.ViewModel

open class ReceiverFragmentViewModel : ViewModel() {
        private var tvMessage: String? = null

        fun writeMessage(message: String){
            tvMessage = message
        }

        fun getMessage():String?{
            return tvMessage
        }
}