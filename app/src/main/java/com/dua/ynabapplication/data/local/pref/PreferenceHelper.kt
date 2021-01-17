package com.dua.ynabapplication.data.local.pref


interface PreferenceHelper {

    var baseUrl: String
    var isTransactionPersistent: Boolean
    var userRole: String
    var remoteApiVersion: String
    var serverVersion: String
    var userOs: String
    var certValue: String
    var languagePref: String
    var nightModeEnabled: Boolean
    var isKeyguardEnabled: Boolean
    var timeFormat: Boolean
    var isCustomCa: Boolean
    var isCurrencyThumbnailEnabled: Boolean
    var workManagerDelay: Long
    var workManagerLowBattery: Boolean
//    var workManagerNetworkType: NetworkType
    var workManagerRequireCharging: Boolean
    fun clearPref()
}