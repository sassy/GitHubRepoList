package io.github.sassy.githubrepolist.ui.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.sassy.githubrepolist.repository.LoginRepository
import io.github.sassy.githubrepolist.repository.UserRepository
import io.github.sassy.githubrepolist.vo.User
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val loginRepository: LoginRepository
): ViewModel() {
    var user: MutableLiveData<User> = MutableLiveData()

    fun fetchUser() {
        userRepository.fetch(loginRepository.login!!).subscribe{user ->
            this.user.postValue(user)
        }
    }
}
