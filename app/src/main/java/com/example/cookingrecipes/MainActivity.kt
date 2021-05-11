package com.example.cookingrecipes

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.navigation.findNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.ui.*
import com.example.cookingrecipes.R.id.nav_host_fragment
import com.example.cookingrecipes.R.id.nav_logout
import com.example.cookingrecipes.data.storage.SharedPreferenceManager
import com.example.cookingrecipes.ui.AllRecipes.AllRecipes
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(R.id.nav_allRecipes,
            R.id.nav_yourprofile, R.id.nav_savedRecipes, R.id.nav_login), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setNavigationItemSelectedListener {
            dest-> when(dest.itemId){
                R.id.nav_logout -> logout()
            else-> {
                NavigationUI.onNavDestinationSelected(dest, navController)
            }
            }
            true
        }
        if(SharedPreferenceManager.getInstance(this).isLoggedIn !="") {
            navView.menu.clear()
            navView.inflateMenu(R.menu.activity_main_drawer_when_logged_in)
        }
        if(SharedPreferenceManager.getInstance(this).isLoggedIn==""){
            navView.menu.clear()
            navView.inflateMenu(R.menu.activity_main_drawer)

        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    private fun logout(){
        SharedPreferenceManager.getInstance(this).clearUser()
        intent =Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}
