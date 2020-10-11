package com.dicoding.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

class FavoriteActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        navController = this.findNavController(R.id.favoriteFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)

        setupToolbar()
    }

    override fun onSupportNavigateUp(): Boolean {

        return navController.navigateUp()
    }

    //setup Toolbar
    private fun setupToolbar() {
        supportActionBar?.apply {
            this.title = "Favorite Kata"
            this.setDisplayHomeAsUpEnabled(true)
            this.setDisplayShowHomeEnabled(true)
        }
    }

    //Handler Toolbar Menu
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}