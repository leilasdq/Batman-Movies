package com.example.batman_project.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.batman_project.R

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration : AppBarConfiguration
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        //requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LOCKED
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val navController = this.findNavController(R.id.my_nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, null)
        appBarConfiguration = AppBarConfiguration(navController.graph, null)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.my_nav_host_fragment)
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }
}
