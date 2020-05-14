package io.github.sassy.githubrepolist.ui.login

import androidx.lifecycle.ViewModel
import io.github.sassy.githubrepolist.repository.LoginRepository
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {
    fun setLogin(loginName: String) {
        loginRepository.login = loginName
    }
}
