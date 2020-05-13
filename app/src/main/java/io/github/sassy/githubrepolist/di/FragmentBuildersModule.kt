package io.github.sassy.githubrepolist.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.sassy.githubrepolist.ui.repo.RepoFragment

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeRepoFragment(): RepoFragment
}