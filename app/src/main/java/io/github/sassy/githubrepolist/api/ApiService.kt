package io.github.sassy.githubrepolist.api

import io.github.sassy.githubrepolist.vo.Repo
import io.github.sassy.githubrepolist.vo.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("users/{login}")
    fun getUser(@Path("login") login: String): Observable<User>
    @GET("users/{login}/repos")
    fun getRepos(@Path("login") login: String): Observable<List<Repo>>
}