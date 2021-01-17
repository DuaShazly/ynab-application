package com.dua.ynabapplication.repository.models

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.dua.ynabapplication.data.local.pref.AppPref


class GlobalViewModel(application: Application): AndroidViewModel(application) {

    private lateinit var prefListener: SharedPreferences.OnSharedPreferenceChangeListener
    var isDark: Boolean = false
        private set

    fun isDarkMode(): MutableLiveData<Boolean>{
        val darkModeLiveData: MutableLiveData<Boolean> = MutableLiveData()
        val sharedPreferenceManager = PreferenceManager.getDefaultSharedPreferences(getApplication())
        val appPreference = AppPref(PreferenceManager.getDefaultSharedPreferences(getApplication()))
        prefListener = SharedPreferences.OnSharedPreferenceChangeListener{ _, key ->
            if (key == "night_mode") {
                if (appPreference.nightModeEnabled) {
                    isDark =  true
                    darkModeLiveData.postValue(true)
                } else {
                    isDark = false
                    darkModeLiveData.postValue(false)
                }
            }
        }
        sharedPreferenceManager.registerOnSharedPreferenceChangeListener(prefListener)
        if(appPreference.nightModeEnabled){
            isDark =  true
            darkModeLiveData.postValue(true)
        } else {
            isDark =  false
            darkModeLiveData.postValue(false)
        }
        return darkModeLiveData
    }

}