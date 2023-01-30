package com.listview.muvi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.listview.muvi.R
import com.listview.muvi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var getBinding: ActivityMainBinding? = null
    private val binding get() = getBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val navView = binding?.navView
        val navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration =  AppBarConfiguration.Builder(
            R.id.navigation_now_playing,R.id.navigation_popular,R.id.navigation_top_rated,R.id.navigation_up_coming
        ).build()

        setupActionBarWithNavController(navController,appBarConfiguration)
        navView?.setupWithNavController(navController)
        supportActionBar?.hide()
    }
}