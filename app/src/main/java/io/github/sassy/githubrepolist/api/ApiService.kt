package io.github.sassy.githubrepolist.api

import io.github.sassy.githubrepolist.vo.Repo
import io.github.sassy.githubrepolist.vo.User
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {
    @GET("users/sassy")
    fun getUser(): Observable<User>
    @GET("users/sassy/repos")
    fun getRepos(): Observable<List<Repo>>
}