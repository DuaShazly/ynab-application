package com.dua.ynabapplication.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.observe
import com.dua.ynabapplication.R
import com.dua.ynabapplication.data.local.pref.AppPref
import com.dua.ynabapplication.repository.models.GlobalViewModel
import com.dua.ynabapplication.utils.extension.getViewModel


@SuppressLint("Registered")
open class BaseActivity: AppCompatActivity() {

    protected val globalViewModel by lazy { getViewModel(GlobalViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme()
    }

    private fun setTheme(){
//        globalViewModel.isDarkMode().observe(this){ isDark ->
//                            setTheme(R.style.Theme_YnabApplication)
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO
//        }
    }

    override fun attachBaseContext(newBase: Context) {
//        super.attachBaseContext(LanguageChanger.init(newBase,
//                sharedPref(newBase).languagePref))
    }

    override fun applyOverrideConfiguration(overrideConfiguration: Configuration) {
        val uiMode = overrideConfiguration.uiMode
        overrideConfiguration.setTo(baseContext.resources.configuration)
        overrideConfiguration.uiMode = uiMode
        super.applyOverrideConfiguration(overrideConfiguration)
    }

    protected fun sharedPref(context: Context): AppPref {
        return AppPref(PreferenceManager.getDefaultSharedPreferences(context))
    }
}