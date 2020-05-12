package io.github.sassy.githubrepolist.di

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import io.github.sassy.githubrepolist.GitHubRepoListApp
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        MainActivityModule::class
    ]
)
interface AppComponent: AndroidInjector<GitHubRepoListApp> {
    @Component.Factory
    abstract class Factory: AndroidInjector.Factory<GitHubRepoListApp>
}