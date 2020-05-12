package io.github.sassy.githubrepolist.di

import dagger.Module
import dagger.Provides
import io.github.sassy.githubrepolist.repository.RepoRepository
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun providerRepoRepository() = RepoRepository()
}