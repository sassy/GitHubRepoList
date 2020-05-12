package io.github.sassy.githubrepolist.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.sassy.githubrepolist.MainActivity

@Suppress("unused")
@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity
}