package io.github.sassy.githubrepolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.github.sassy.githubrepolist.ui.RepoFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity(),
    RepoFragment.OnListFragmentInteractionListener,
    HasAndroidInjector {

    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onListFragmentInteraction(item: String?) {
        // do nothing
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}
