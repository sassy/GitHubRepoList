package io.github.sassy.githubrepolist.di

import dagger.Module
import dagger.Provides
import io.github.sassy.githubrepolist.api.ApiService
import io.github.sassy.githubrepolist.repository.LoginRepository
import io.github.sassy.githubrepolist.repository.RepoRepository
import io.github.sassy.githubrepolist.repository.UserRepository
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideApiService() : ApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun providerRepoRepository(apiService: ApiService) = RepoRepository(apiService)

    @Singleton
    @Provides
    fun providerUserRepository(apiService: ApiService) = UserRepository(apiService)

    @Singleton
    @Provides
    fun providerLoginRepository() = LoginRepository()
}