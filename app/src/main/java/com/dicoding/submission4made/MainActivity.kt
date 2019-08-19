package com.dicoding.submission4made

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.Settings.ACTION_LOCALE_SETTINGS
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.dicoding.submission4made.favorite.FavoriteFragment
import com.dicoding.submission4made.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var selectedFragmentName = "home"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.elevation = 0.0f

        loadFragment(HomeFragment())
        //selectedFragmentName = "home"


//        if (savedInstanceState != null)
//        {
//            Log.d("TES123", "selected 2 = "+selectedFragmentName)
//            if (selectedFragmentName == "home"){
//                loadFragment(HomeFragment())
//                bottom_nav_main.selectedItemId = R.id.menu_bottom_nav_home
//            }
//            else if (selectedFragmentName == "favorite"){
//                loadFragment(FavoriteFragment())
//                bottom_nav_main.selectedItemId = R.id.menu_bottom_nav_favorite
//
//            }
//        }

        bottom_nav_main.setOnNavigationItemSelectedListener {
            when{
                it.itemId == R.id.menu_bottom_nav_home ->
                {
                    selectedFragmentName = "home"
                    loadFragment(HomeFragment())
                }
                it.itemId == R.id.menu_bottom_nav_favorite ->
                {
                    selectedFragmentName = "favorite"
                    loadFragment(FavoriteFragment())
                }

                else ->
                {
                    selectedFragmentName = "home"
                    loadFragment(HomeFragment())
                }

            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.action_change_settings){
            val intent = Intent(ACTION_LOCALE_SETTINGS)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    fun loadFragment(fragment: androidx.fragment.app.Fragment): Boolean {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
        return true
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putString("selectedFragmentName", selectedFragmentName)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        selectedFragmentName = savedInstanceState?.getString("selectedFragmentName").toString()
        Log.d("TES123", "selected = "+selectedFragmentName)

        if (selectedFragmentName == "home"){
            loadFragment(HomeFragment())
            bottom_nav_main.selectedItemId = R.id.menu_bottom_nav_home
        }
        else if (selectedFragmentName == "favorite"){
            loadFragment(FavoriteFragment())
            bottom_nav_main.selectedItemId = R.id.menu_bottom_nav_favorite

        }
    }
}
