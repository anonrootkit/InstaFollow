package com.example.instafollow.screen

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import com.example.instafollow.databinding.UserListItemBinding

class RecyclerViewAdapter
    : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private lateinit var onUserFollowClickListener: OnUserFollowClickListener
    private val users : ArrayList<User> = ArrayList()

    fun initList(list : List<User>) {
        users.clear()
        users.addAll(list)
        this.notifyDataSetChanged()
    }

    fun initFollowButtonListener(onUserFollowClickListener: OnUserFollowClickListener) {
        this.onUserFollowClickListener = onUserFollowClickListener
    }

    class ViewHolder(val userListItemBinding: UserListItemBinding) : RecyclerView.ViewHolder(userListItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val listItemBinding : UserListItemBinding =
            UserListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user : User = users[position]
        holder.userListItemBinding.name.text = user.name
        holder.userListItemBinding.bio.text = user.bio

        when(user.followStatus){
            FollowStatus.FOLLOWING -> {
                holder.userListItemBinding.followButton.apply {
                    text = "FOLLOWING"
                    setBackgroundColor(Color.WHITE)
                    setTextColor("#986789".toColorInt())
                }
            }

            FollowStatus.NOT_FOLLOWING -> {
                holder.userListItemBinding.followButton.apply {
                    text = "FOLLOW"
                    setBackgroundColor("#986789".toColorInt())
                    setTextColor(Color.WHITE)
                }
            }

            FollowStatus.REQUESTED -> {
                holder.userListItemBinding.followButton.apply {
                    text = "REQUESTED"
                    setBackgroundColor(Color.WHITE)
                    setTextColor("#986789".toColorInt())
                }
            }
        }

        holder.userListItemBinding.followButton.setOnClickListener {
            onUserFollowClickListener.onFollowButtonClicked(position)
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }
}

interface OnUserFollowClickListener{
    fun onFollowButtonClicked(position : Int)
}