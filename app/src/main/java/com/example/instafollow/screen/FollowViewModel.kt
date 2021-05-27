package com.example.instafollow.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FollowViewModel : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users : LiveData<List<User>>
        get() = _users

    init {
        _users.value = userList
    }

    fun onFollowButtonClicked(position : Int) {
        val currentList = _users.value ?: return
        val newList : ArrayList<User> = ArrayList<User>().apply { addAll(currentList) }

        val currentUserFollowClicked = currentList[position]
        newList.removeAt(position)
        val updateUserData = User(
            name = currentUserFollowClicked.name,
            bio = currentUserFollowClicked.bio,
            followStatus = getUpdateFollowStatus(currentUserFollowStatus = currentUserFollowClicked.followStatus))
        newList.add(position, updateUserData)

        _users.value = newList
    }

    private fun getUpdateFollowStatus(currentUserFollowStatus: FollowStatus) : FollowStatus {
        return when {
            currentUserFollowStatus != FollowStatus.NOT_FOLLOWING -> {
                FollowStatus.NOT_FOLLOWING
            }

            isProfilePublic() -> {
                FollowStatus.FOLLOWING
            }

            else -> {
                FollowStatus.REQUESTED
            }
        }
    }

    class Factory : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return FollowViewModel() as T
        }
    }
}

fun isProfilePublic(): Boolean {
    val range = 0..1
    return range.random() == 0
}

val userList = ArrayList<User>().apply {
    add(User("Ankit", "Cool dude!!!"))
    add(User("Abhishek", "Kakashi senpaiiiiii"))
    add(User("Ansh", "Hehehehehehe"))
    add(User("Ayush", "To the mooonnn"))
    add(User("Ankit", "Cool dude!!!"))
    add(User("Ansh", "hehehehe"))
    add(User("Ansh", "What kind girl ur preference"))
}

data class User(
    val name : String,
    val bio : String,
    val followStatus: FollowStatus = FollowStatus.NOT_FOLLOWING
)


enum class FollowStatus{
    FOLLOWING,
    NOT_FOLLOWING,
    REQUESTED
}