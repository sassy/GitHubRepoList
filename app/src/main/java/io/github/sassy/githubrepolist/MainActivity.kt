package io.github.sassy.githubrepolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.github.sassy.githubrepolist.R
import io.github.sassy.githubrepolist.ui.RepoFragment

class MainActivity : AppCompatActivity(), RepoFragment.OnListFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onListFragmentInteraction(item: String?) {
        // do nothing
    }
}
