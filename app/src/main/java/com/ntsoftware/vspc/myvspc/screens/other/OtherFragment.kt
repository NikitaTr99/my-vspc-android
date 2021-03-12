package com.ntsoftware.vspc.myvspc.screens.other

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.navigation.NavigationView
import com.ntsoftware.vspc.myvspc.MainActivity
import com.ntsoftware.vspc.myvspc.R

class OtherFragment: Fragment() {

    private lateinit var navigation_view: NavigationView

    private lateinit var scroll_layout_params: CoordinatorLayout.LayoutParams

    private lateinit var scroll_layout_behavior: CoordinatorLayout.Behavior<CoordinatorLayout>

    private lateinit var main_activity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root: View = inflater.inflate(R.layout.fragment_other, container, false)

        navigation_view = root.findViewById(R.id.other_nav_view)

        main_activity = activity as MainActivity

        (activity as MainActivity).supportActionBar?.hide()

        scroll_layout_params = main_activity.scroll_layout.layoutParams as CoordinatorLayout.LayoutParams

        //TODO Вылеты при повтороном нажатии пункта меню
        if(scroll_layout_params.behavior != null) {
            scroll_layout_behavior = scroll_layout_params.behavior as CoordinatorLayout.Behavior<CoordinatorLayout>
        }

        scroll_layout_params.behavior = null

        navigation_view.setNavigationItemSelectedListener(menu_item_selected)

        return root
    }

    override fun onStop() {
        super.onStop()
        scroll_layout_params.behavior = scroll_layout_behavior
    }

    private val menu_item_selected: NavigationView.OnNavigationItemSelectedListener = NavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {

            R.id.nav_settings -> main_activity.nav_controller.navigate(R.id.nav_settings_activity)

            R.id.nav_web_home -> openWebSite("https://www.vspc34.ru/")
            R.id.nav_web_enrollee -> openWebSite("https://www.vspc34.ru/index.php?option=com_content&view=category&id=9&Itemid=22")
            R.id.nav_web_student -> openWebSite("https://www.vspc34.ru/index.php?option=com_content&view=category&id=50&Itemid=42")
            R.id.nav_web_about -> openWebSite("https://www.vspc34.ru/index.php?option=com_content&view=category&id=104&Itemid=152")

            R.id.nav_vk -> openWebSite("https://vk.com/true_vspc34")
            R.id.nav_instagram -> openWebSite("https://www.instagram.com/vspcinst")
            R.id.nav_youtube -> openWebSite("https://www.youtube.com/channel/UCuzlglODRI-VG4Qhu58tmRQ")

            else -> Toast.makeText(context, "No support", Toast.LENGTH_SHORT).show()
        }
        false
    }

    private fun openWebSite(url: String) {
        try {
            context?.let { CustomTabsIntent.Builder().build().launchUrl(it, Uri.parse(url)) }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, getString(R.string.nav_open_site_error_toast), Toast.LENGTH_SHORT).show()
        }
    }
}