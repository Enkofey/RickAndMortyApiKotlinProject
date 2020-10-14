package com.example.myfirstprograminkotlin.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myfirstprograminkotlin.R
import com.example.myfirstprograminkotlin.ui.fragments.CharacterFragment
import com.example.myfirstprograminkotlin.ui.fragments.EpisodeFragment
import com.example.myfirstprograminkotlin.ui.fragments.AccountFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.menuNavigationBar)
        bottomNav.setOnNavigationItemSelectedListener(navListener)
        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            CharacterFragment()
        ).commit()
    }

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            when (item.itemId) {
                R.id.nav_character -> selectedFragment = CharacterFragment()
                R.id.nav_episode -> selectedFragment = EpisodeFragment()
                R.id.nav_account -> selectedFragment = AccountFragment()
            }
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fragment_fade_enter,R.anim.fragment_fade_exit,R.anim.fragment_fade_enter,R.anim.fragment_fade_exit)
                .replace(R.id.fragment_container, selectedFragment!!).commit()
            true
    }
}