package io.github.sassy.githubrepolist.ui.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import io.github.sassy.githubrepolist.repository.RepoRepository
import io.github.sassy.githubrepolist.vo.Repo
import javax.inject.Inject

class RepoViewModel @Inject constructor(private val repository: RepoRepository) : ViewModel() {
    private val repos: MutableLiveData<List<Repo>> = MutableLiveData()
    private var filterText: MutableLiveData<String> = MutableLiveData()
    val reposFullNames: LiveData<List<String>> = Transformations.map(filterText, { text ->
        repos.value!!.map { repo ->
            repo.fullName
        }.filter{str ->
            if (text.isNullOrEmpty()) true else str.contains(text, true)
        }
    })

    init {
        fetchRepos()
    }

    fun fetchRepos()  {
        repository.fetch().subscribe({ list ->
            repos.postValue(list)
            filterText.postValue("")
        })
    }

    fun searchRequest(text: String) {
        filterText.postValue(text)
    }

    fun getCount() = if (reposFullNames.value == null) 1 else reposFullNames.value!!.size
}