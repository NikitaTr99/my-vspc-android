package com.ntsoftware.vspc.myvspc

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textview.MaterialTextView

class MainActivity: AppCompatActivity() {

    private lateinit var app_bar_config: AppBarConfiguration

    private lateinit var navigation_view: BottomNavigationView

    private lateinit var toolbar: Toolbar

    lateinit var nav_controller: NavController

    lateinit var scroll_layout: CoordinatorLayout

    val status_text_view: MaterialTextView by lazy {
        findViewById(R.id.screen_status)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.Theme_MyVSPC_NoActionBar)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        init()

        setSupportActionBar(toolbar)

        setupNavigation()
    }

    private fun setupNavigation() {
        app_bar_config = AppBarConfiguration.Builder(
                R.id.nav_discover,R.id.nav_news, R.id.nav_schedule, R.id.nav_other)
                .build()

        nav_controller = Navigation.findNavController(this, R.id.nav_host_fragment)

        NavigationUI.setupActionBarWithNavController(this, nav_controller, app_bar_config)

        NavigationUI.setupWithNavController(navigation_view, nav_controller)

    }

    private fun init() {
        scroll_layout = findViewById(R.id.scroll_layout)
        navigation_view = findViewById(R.id.bottom_nav)
        toolbar = findViewById(R.id.toolbar)
    }

}