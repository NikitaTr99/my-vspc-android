package com.ntsoftware.vspc.myvspc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textview.MaterialTextView
import com.ntsoftware.vspc.myvspc.storage.ScheduleCache

class MainActivity: AppCompatActivity() {

    private lateinit var app_bar_config: AppBarConfiguration

    private lateinit var navigation_view: BottomNavigationView

    private lateinit var toolbar: Toolbar

    lateinit var nav_controller: NavController

    lateinit var scroll_layout: CoordinatorLayout

    lateinit var scroll_layout_params: CoordinatorLayout.LayoutParams

    lateinit var scroll_layout_behavior: CoordinatorLayout.Behavior<CoordinatorLayout>

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
                R.id.nav_vk, R.id.nav_instagram, R.id.nav_youtube, R.id.nav_other)
                .build()

        nav_controller = Navigation.findNavController(this, R.id.nav_host_fragment)

        NavigationUI.setupActionBarWithNavController(this, nav_controller, app_bar_config)

        NavigationUI.setupWithNavController(navigation_view, nav_controller)

    }

    private fun init() {
        scroll_layout = findViewById(R.id.scroll_layout)
        navigation_view = findViewById(R.id.bottom_nav)
        toolbar = findViewById(R.id.toolbar)

        scroll_layout_params = scroll_layout.layoutParams as CoordinatorLayout.LayoutParams

        if(scroll_layout_params.behavior != null) {
            scroll_layout_behavior = scroll_layout_params.behavior as CoordinatorLayout.Behavior<CoordinatorLayout>
        }

    }

    public fun needBehavior(on: Boolean) {
        if(on) {
            scroll_layout_params.behavior = scroll_layout_behavior
        }
        else {
            scroll_layout_params.behavior = null
        }
    }

}