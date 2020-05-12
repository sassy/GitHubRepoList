package io.github.sassy.githubrepolist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import io.github.sassy.githubrepolist.repository.RepoRepository
import io.github.sassy.githubrepolist.vo.Repo
import javax.inject.Inject

class RepoViewModel @Inject constructor(repository: RepoRepository) : ViewModel() {
    private val repos: MutableLiveData<List<Repo>> = MutableLiveData()
    private var filterText: MutableLiveData<String> = MutableLiveData()
    private val repository = repository

    val reposFullNames: LiveData<List<String>> = Transformations.map(filterText, { text ->
        repos.value!!.map { repo ->
            repo.fullName
        }.filter{str ->
            if (text.isNullOrEmpty()) true else str.contains(text, true)
        }
    })

    fun fetchRepos()  {
        repository.fetch().subscribe({ list ->
            repos.postValue(list)
            filterText.postValue("")
        })
    }

    fun searchRequest(text: String) {
        filterText.postValue(text)
    }
}