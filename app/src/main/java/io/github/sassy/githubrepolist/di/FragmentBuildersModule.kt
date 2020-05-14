package io.github.sassy.githubrepolist.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.sassy.githubrepolist.ui.login.LoginFragment
import io.github.sassy.githubrepolist.ui.repo.RepoFragment
import io.github.sassy.githubrepolist.ui.user.UserFragment

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeRepoFragment(): RepoFragment

    @ContributesAndroidInjector
    abstract fun contributeUserFragment(): UserFragment

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment
}