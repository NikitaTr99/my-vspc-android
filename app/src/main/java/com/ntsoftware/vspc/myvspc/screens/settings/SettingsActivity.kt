package com.ntsoftware.vspc.myvspc.screens.settings

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.ntsoftware.vspc.myvspc.BuildConfig
import com.ntsoftware.vspc.myvspc.R
import java.net.URI

class SettingsActivity : AppCompatActivity() {

    private lateinit var tool_bar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        tool_bar = findViewById(R.id.set_toolbar)

        setSupportActionBar(tool_bar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Настройки"

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    class SettingsFragment: PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_pref, rootKey)

            findPreference<Preference>("settings_contact_developer")?.setOnPreferenceClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/Tremper")))
                true
            }

            findPreference<Preference>("version")?.summary = "Версия приложения ${BuildConfig.VERSION_NAME}"

        }
    }

}