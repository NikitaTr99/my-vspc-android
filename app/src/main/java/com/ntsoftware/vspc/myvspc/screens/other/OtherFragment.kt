package com.ntsoftware.vspc.myvspc.screens.other

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.marginTop
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.navigation.NavigationView
import com.ntsoftware.vspc.myvspc.MainActivity
import com.ntsoftware.vspc.myvspc.R
import com.ntsoftware.vspc.myvspc.ui.OtherMenuItem

class OtherFragment: Fragment() {

    private lateinit var navigation_view: NavigationView

    private lateinit var scroll_layout_params: CoordinatorLayout.LayoutParams

    private lateinit var main_activity: MainActivity

    private val items_id: Array<Int> = arrayOf(
            R.id.other_menu_site_home,
            R.id.other_menu_site_enrollee,
            R.id.other_menu_site_student,
            R.id.other_menu_site_about,
            R.id.other_menu_social_vk,
            R.id.other_menu_social_instagram,
            R.id.other_menu_social_youtube,
            R.id.other_menu_settings
    )

    private val menu_items = ArrayList<OtherMenuItem>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root: View = inflater.inflate(R.layout.fragment_other2, container, false)

        main_activity = activity as MainActivity

        (activity as MainActivity).supportActionBar?.hide()

        main_activity.needBehavior(false)

        for (id in items_id) {
            val item = root.findViewById<OtherMenuItem>(id)
            item.setOnClickListener(menu_item_selected)
            if (item != null) menu_items.add(item)
        }

        return root
    }

    private val menu_item_selected: View.OnClickListener = View.OnClickListener { v ->
        when (v.id) {

            R.id.other_menu_settings -> main_activity.nav_controller.navigate(R.id.nav_settings_activity)

            R.id.other_menu_site_home -> openWebSite("https://www.vspc34.ru/")
            R.id.other_menu_site_enrollee -> openWebSite("https://www.vspc34.ru/index.php?option=com_content&view=category&id=9&Itemid=22")
            R.id.other_menu_site_student -> openWebSite("https://www.vspc34.ru/index.php?option=com_content&view=category&id=50&Itemid=42")
            R.id.other_menu_site_about -> openWebSite("https://www.vspc34.ru/index.php?option=com_content&view=category&id=104&Itemid=152")

            R.id.other_menu_social_vk -> openWebSite("https://vk.com/true_vspc34")
            R.id.other_menu_social_instagram -> openWebSite("https://www.instagram.com/vspcinst")
            R.id.other_menu_social_youtube -> openWebSite("https://www.youtube.com/channel/UCuzlglODRI-VG4Qhu58tmRQ")

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