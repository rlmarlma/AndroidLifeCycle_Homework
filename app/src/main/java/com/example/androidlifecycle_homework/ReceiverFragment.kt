package com.example.androidlifecycle_homework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import org.w3c.dom.Text

class ReceiverFragment : Fragment() {
    private lateinit var viewModel: ReceiverFragmentViewModel
    private val MessagesAreRead: String = "All messages are read."

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_receiver, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ReceiverFragmentViewModel::class.java)

        if(viewModel.getMessage() == null) {
            parentFragmentManager.setFragmentResultListener(
                SenderFragment.FRAGMENT_REQUEST_KEY,
                this
            ) { requestKey, result ->
                if (requestKey == SenderFragment.FRAGMENT_REQUEST_KEY) {
                    val messageView = view.findViewById<TextView>(R.id.messageReceived)
                    messageView.text = result.getString(SenderFragment.TEXT_KEY)
                    viewModel.writeMessage(messageView.text.toString())
                }
            }
        }else{
            view.findViewById<TextView>(R.id.messageReceived).text = viewModel.getMessage()
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                backToSender()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(callback)

        view.findViewById<Button>(R.id.buttonReadAll).setOnClickListener(){
            view.findViewById<TextView>(R.id.messageReceived).text = MessagesAreRead
            viewModel.writeMessage(MessagesAreRead)
        }
    }

    fun backToSender() {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, SenderFragment())
            .commit()
    }

}



