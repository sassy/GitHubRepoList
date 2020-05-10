package io.github.sassy.githubrepolist.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.sassy.githubrepolist.repository.RepoRepository
import io.github.sassy.githubrepolist.vo.Repo
import io.github.sassy.githubrepolist.vo.dummpyRepo

class RepoViewModel(
    private val repository: RepoRepository
) : ViewModel() {
    val repos: MutableLiveData<List<Repo>> = MutableLiveData(listOf(dummpyRepo))

    fun fetchRepos()  {
        repository.fetch().subscribe({ list ->
            repos.postValue(list)
        })
    }
}