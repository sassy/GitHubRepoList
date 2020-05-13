package io.github.sassy.githubrepolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.github.sassy.githubrepolist.ui.repo.RepoFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity(),
    RepoFragment.OnListFragmentInteractionListener,
    HasAndroidInjector {

    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Any>
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigationDrawer()
        setSupportActionBar(findViewById(R.id.toolbar))

        val navController: NavController = findNavController(R.id.fragment)
        appBarConfiguration = AppBarConfiguration.Builder(R.id.mainFragment)
            .setDrawerLayout(drawerLayout)
            .build()
        setupActionBarWithNavController(navController, appBarConfiguration)
        findViewById<NavigationView>(R.id.navView).setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.fragment).navigateUp(appBarConfiguration) ||  super.onSupportNavigateUp()
    }

    private fun setupNavigationDrawer() {
        drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout).apply {
            setStatusBarBackground(R.color.colorPrimaryDark)
        }
    }

    override fun onListFragmentInteraction(item: String?) {
        // do nothing
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}
