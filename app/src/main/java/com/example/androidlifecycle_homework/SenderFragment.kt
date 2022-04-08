package com.example.androidlifecycle_homework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

class SenderFragment : Fragment() {

    companion object {

        const val FRAGMENT_REQUEST_KEY = "FRAGMENT_REQUEST_KEY"
        const val TEXT_KEY = "TEXT_KEY"

        fun newInstance(messageText: String) : Fragment {
            return SenderFragment().apply {
                val bundle = Bundle()
                bundle.putString(TEXT_KEY, messageText)
                arguments = bundle
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_sender, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.sendButton).setOnClickListener{
            val messageText = view.findViewById<EditText>(R.id.messageTypeIn).text.toString()
            val result = Bundle()
            result.putString(TEXT_KEY, messageText)
            parentFragmentManager.setFragmentResult(FRAGMENT_REQUEST_KEY, result)
            replaceFragment()
        }

    }

    private fun replaceFragment(){
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, ReceiverFragment())
            .commit()
    }
}