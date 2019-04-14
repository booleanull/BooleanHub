package com.boolenull.booleanhub.ui

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.boolenull.booleanhub.R
import com.boolenull.booleanhub.ui.adapter.PageAdapter
import com.boolenull.booleanhub.ui.fragment.EnumFragment.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_blog -> {
                    viewPager.currentItem = BLOG.ordinal
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_repository -> {
                    viewPager.currentItem = REPOSITORY.ordinal
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_aboutme -> {
                    viewPager.currentItem = LINK.ordinal
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager.adapter = PageAdapter(supportFragmentManager)
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }
}
