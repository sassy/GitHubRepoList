package io.github.sassy.githubrepolist.repository

import io.github.sassy.githubrepolist.api.ApiService
import io.github.sassy.githubrepolist.vo.Repo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepoRepository @Inject constructor(
    private val apiService: ApiService
) {
    private var repos: List<Repo>? = null

    fun fetch(login: String) : Observable<List<Repo>> {
        return apiService.getRepos(login)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
    }
}