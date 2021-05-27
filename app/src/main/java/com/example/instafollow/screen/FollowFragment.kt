package com.example.instafollow.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.instafollow.R
import com.example.instafollow.databinding.FragmentFollowBinding

class FollowFragment : Fragment(), OnUserFollowClickListener {

    private lateinit var binding : FragmentFollowBinding
    private lateinit var viewModel: FollowViewModel
    private lateinit var userAdapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, FollowViewModel.Factory()).get(FollowViewModel::class.java)
        userAdapter = RecyclerViewAdapter()
        userAdapter.initFollowButtonListener(this)

        binding.usersList.adapter = userAdapter

        viewModel.users.observe(viewLifecycleOwner) { users ->
            if (!users.isNullOrEmpty()){
                userAdapter.initList(users)
            }
        }
    }

    override fun onFollowButtonClicked(position: Int) {
        viewModel.onFollowButtonClicked(position)
    }
}