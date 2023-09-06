package com.example.mechaapp.features.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mechaapp.databinding.FragmentChatBinding
import com.example.mechaapp.data.adapter.ChatAdapter
import com.example.mechaapp.data.Model.DataChat

class ChatFragment : Fragment() {
    private  lateinit var binding: FragmentChatBinding
    private val chatAdapter: ChatAdapter by lazy { ChatAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(activity)
        chatAdapter.submitList(DataChat.chatList)
        binding.rvChat.adapter = chatAdapter
        binding.rvChat.layoutManager = layoutManager

    }
}