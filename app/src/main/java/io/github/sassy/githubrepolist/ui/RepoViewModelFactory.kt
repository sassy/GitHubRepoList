package io.github.sassy.githubrepolist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.sassy.githubrepolist.repository.RepoRepository

class RepoViewModelFactory(
  private val repository: RepoRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RepoViewModel(repository) as T
    }
}