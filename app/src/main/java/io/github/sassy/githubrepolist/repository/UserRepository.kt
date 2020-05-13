package io.github.sassy.githubrepolist.repository

import io.reactivex.Observable
import io.github.sassy.githubrepolist.api.ApiService
import io.github.sassy.githubrepolist.vo.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val apiService: ApiService
){
    fun fetch(): Observable<User> {
        return apiService.getUser()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
    }
}