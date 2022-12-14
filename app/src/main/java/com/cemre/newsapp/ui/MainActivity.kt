package com.cemre.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.cemre.newsapp.R
import com.cemre.newsapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    //private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        appBarConfiguration = AppBarConfiguration(setOf(R.id.newsFragment, R.id.favoritesFragment, R.id.profileFragment))


        setSupportActionBar(toolbar)

        setupActionBarWithNavController(navController, appBarConfiguration)


        val bottom_nav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottom_nav.setupWithNavController(navController)

        // hiding bottom_nav in web view
        navController.addOnDestinationChangedListener { _, destination, _ ->

            when (destination.id) {

                R.id.newsSourceFragment -> bottom_nav.visibility = View.GONE

                else -> bottom_nav.visibility = View.VISIBLE

            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}