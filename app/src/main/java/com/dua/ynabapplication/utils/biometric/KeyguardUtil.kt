package com.dua.ynabapplication.utils.biometric

import android.app.Activity
import android.app.KeyguardManager
import android.content.Context
import android.preference.PreferenceManager
import com.dua.ynabapplication.data.local.pref.AppPref


class KeyguardUtil(private val activity: Activity) {

    fun isDeviceKeyguardEnabled(): Boolean{
        val keyguardManager  = activity.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        return keyguardManager.isKeyguardSecure
    }

    fun isAppKeyguardEnabled(): Boolean{
        return AppPref(PreferenceManager.getDefaultSharedPreferences(activity)).isKeyguardEnabled
    }

}