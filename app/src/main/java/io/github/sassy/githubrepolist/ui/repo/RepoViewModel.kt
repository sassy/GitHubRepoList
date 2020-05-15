package io.github.sassy.githubrepolist.ui.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import io.github.sassy.githubrepolist.repository.LoginRepository
import io.github.sassy.githubrepolist.repository.RepoRepository
import io.github.sassy.githubrepolist.vo.Repo
import javax.inject.Inject

class RepoViewModel @Inject constructor(
    private val repository: RepoRepository,
    private val loginRepository: LoginRepository
) : ViewModel() {
    private val repos: MutableLiveData<List<Repo>> = MutableLiveData()
    private var filterText: MutableLiveData<String> = MutableLiveData()
    val reposFullNames: LiveData<List<String>> = Transformations.map(filterText, { text ->
        repos.value!!.map { repo ->
            repo.fullName
        }.filter{str ->
            if (text.isNullOrEmpty()) true else str.contains(text, true)
        }
    })

    fun fetchRepos()  {
        repository.fetch(loginRepository.login!!).subscribe({ list ->
            repos.postValue(list)
            filterText.postValue("")
        })
    }

    fun searchRequest(text: String) {
        filterText.postValue(text)
    }

    fun getCount() = if (reposFullNames.value == null) 0 else reposFullNames.value!!.size
}