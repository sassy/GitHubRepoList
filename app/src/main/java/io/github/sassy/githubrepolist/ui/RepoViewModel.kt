package io.github.sassy.githubrepolist.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.sassy.githubrepolist.repository.RepoRepository

class RepoViewModel(
    private val repository: RepoRepository
) : ViewModel() {
    val repos: MutableLiveData<List<String>> = MutableLiveData()
    init {
        repos.value = repository.getRepos()
    }
}