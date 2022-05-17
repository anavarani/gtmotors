package com.varani.gtmotors

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.FloatingWindow
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitleDynamically()
    }

    private fun setTitleDynamically() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination !is FloatingWindow) {
                supportActionBar?.title = destination.label
            }
        }
    }
}